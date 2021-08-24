package com.excmul.member.ui;

import com.excmul.auth.oauth.AuthPrincipal;
import com.excmul.common.domain.vo.TokenVo;
import com.excmul.member.application.MemberService;
import com.excmul.member.domain.vo.EmailVo;
import com.excmul.member.domain.vo.PasswordVo;
import com.excmul.member.dto.*;
import com.excmul.member.exception.DuplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @RequestMapping({"", "/", "index"})
    public String index() {
        return "fragments/contents/index";
    }

    @GetMapping("/auth/sign")
    public String sign(Model model) {
        model.addAttribute("defaultMemberSignRequest", new MemberSignRequest());
        return "fragments/contents/member/sign";
    }

    @PostMapping("/auth/sign")
    public String sign(MemberSignRequest request) {
        if (memberService.existsByEmail(request.getEmail())) {
            throw new DuplicationException(DuplicationException.ErrorCode.DUPLICATION_EMAIL);
        }

        if (memberService.existsByNickname(request.getNickname())) {
            throw new DuplicationException(DuplicationException.ErrorCode.DUPLICATION_NICKNAME);
        }

        if (memberService.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new DuplicationException(DuplicationException.ErrorCode.DUPLICATION_PHONE_NUMBER);
        }

        memberService.createDefaultMember(request);
        return "redirect:/auth/login";
    }

    @GetMapping("/auth/login")
    public String login() {
        return "/fragments/contents/member/login";
    }

    // 세션 유진
    @GetMapping("/auth/editPassword")
    public String editPassword(Model model) {
        model.addAttribute("memberChangePasswordRequest", new MemberChangePasswordRequest());
        return "/fragments/contents/member/edit-password";
    }

    @PostMapping("/auth/editPassword")
    public String changePassword(@AuthenticationPrincipal AuthPrincipal principal, MemberChangePasswordRequest request) {
        // request.getBeforeChangePassword() -> 서비스로 던져줘야
        // 서비스는 request를 몰라야 한다!
        memberService.changeHomePagePassword(principal.loginMember(), request);
        return "fragments/contents/index";
    }

    @GetMapping("/auth/idInquiry")
    public String idInquiry() {
        return "fragments/contents/member/id-inquiry";
    }

    @PostMapping("/auth/idInquiry")
    public String idInquiry(ModelMap modelMap,
                            @ModelAttribute("token") IdInquiryRequest request) {
        Optional<EmailVo> optionalEmail = memberService.inquiryId(
                request.getName(), request.getBirth(), request.getPhoneNumber()
        );

        optionalEmail.ifPresent(emailVo ->
                modelMap.addAttribute("email", emailVo.value())
        );
        return "fragments/contents/member/id-inquiry-result";
    }

    @GetMapping("/auth/pwInquiry")
    public String pwInquiry() {
        return "fragments/contents/member/pw-inquiry";
    }

    @PostMapping("/auth/pwInquiry")
    public String pwInquiry(ModelMap modelMap,
                            PwInquiryRequest request) {
        boolean isSent = memberService.inquiryPw(
                request.getEmail(), request.getName(), request.getBirth(), request.getPhoneNumber()
        );
        modelMap.addAttribute("isSent", isSent);

        return "fragments/contents/member/pw-inquiry-result";
    }

    @GetMapping("/auth/changePassword/{Token}")
    public String changePassword(ModelMap modelMap,
                                 @PathVariable("Token") TokenVo token) {
        boolean isAvailable = memberService.isAvailablePasswordChangeToken(token);

        modelMap.addAttribute("isAvailable", isAvailable);

        return "fragments/contents/member/change-password";
    }

    @PostMapping("/auth/changePassword")
    public String changePassword(@ModelAttribute("token") TokenVo token,
                                 PasswordVo password) {

        memberService.changePassword(token, password);

        return "fragments/contents/member/change-password-result";
    }

    /**
     * 회원 정보 수정
     */
    @GetMapping("/auth/edit")
    public String edit(Model model) {
        EditDto editRequest = new EditDto();
        model.addAttribute("editRequest", editRequest);
        return "/fragments/contents/member/edit";
    }

    @PostMapping("/auth/edit")
    public String edit(@AuthenticationPrincipal AuthPrincipal principal, EditDto editDto) {
        memberService.edit(principal.loginMember().email().toString(), editDto);
        return "redirect:/fragments/contents/index";
    }
}
