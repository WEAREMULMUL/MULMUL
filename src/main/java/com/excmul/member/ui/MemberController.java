package com.excmul.member.ui;

import com.excmul.common.domain.vo.TokenVo;
import com.excmul.member.application.MemberService;
import com.excmul.member.domain.vo.EmailVo;
import com.excmul.member.domain.vo.PasswordVo;
import com.excmul.member.dto.IdInquiryRequest;
import com.excmul.member.dto.MemberSignRequest;
import com.excmul.member.dto.PwInquiryRequest;
import com.excmul.member.exception.DuplicationException;
import lombok.RequiredArgsConstructor;
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
}
