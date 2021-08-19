package com.excmul.member.ui;

<<<<<<< HEAD
import com.excmul.auth.AuthPrincipal;
import com.excmul.member.application.MemberService;
<<<<<<< HEAD
import com.excmul.member.dto.MemberChangePasswordRequest;
=======
import com.excmul.common.domain.vo.TokenVo;
import com.excmul.member.application.MemberService;
import com.excmul.member.domain.vo.EmailVo;
import com.excmul.member.domain.vo.PasswordVo;
import com.excmul.member.dto.IdInquiryRequest;
>>>>>>> origin/jaewon
import com.excmul.member.dto.MemberSignRequest;
import com.excmul.member.dto.PwInquiryRequest;
import com.excmul.member.exception.DuplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
=======
import com.excmul.member.domain.Member;
import com.excmul.member.domain.vo.AuthVo;
import com.excmul.member.domain.vo.PasswordVo;
import com.excmul.member.domain.vo.RoleVo;
import com.excmul.member.dto.MemberSignRequest;
import com.excmul.member.exception.DuplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
>>>>>>> 45411320c0ee2c79afa90449b2f191576f96034f
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
=======
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
>>>>>>> origin/jaewon


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

<<<<<<< HEAD
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
        memberService.changePassword(principal.loginMember(), request);
        return "fragments/contents/index";
    }
    
=======
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
>>>>>>> origin/jaewon
}
