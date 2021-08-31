package com.excmul.member.ui;

import com.excmul.auth.dto.AuthPrincipal;
import com.excmul.auth.exception.OAuth2Exception;
import com.excmul.common.domain.vo.Token;
import com.excmul.member.application.MemberService;
import com.excmul.member.domain.vo.Email;
import com.excmul.member.domain.vo.Password;
import com.excmul.member.dto.*;
import com.excmul.member.exception.DuplicationException;
import com.excmul.member.exception.InvalidInputException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static com.excmul.auth.exception.OAuth2Exception.ErrorCode;

@RequestMapping("auth")
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("sign")
    public String sign(Model model) {
        model.addAttribute("defaultMemberSignRequest", new MemberSignDto());
        return "fragments/contents/member/sign";
    }

    @PostMapping("sign")
    public String sign(@Valid MemberSignDto request) {
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

    @GetMapping("signup/social")
    public String signUpSocial(@AuthenticationPrincipal AuthPrincipal authPrincipal) {
        validateSocialSingUp(authPrincipal);

        return "fragments/contents/member/social-sign-up";
    }

    @PostMapping("signup/social")
    public String signUpSocial(@AuthenticationPrincipal AuthPrincipal authPrincipal,
                               @Valid SocialMemberInformationDto socialMemberInformation) {
        validateSocialSingUp(authPrincipal);

        memberService.updateSocialMemberInfo(authPrincipal.getId(), socialMemberInformation);
        return "fragments/contents/member/social-sign-up-result";
    }

    private void validateSocialSingUp(AuthPrincipal authPrincipal) {
        if (!authPrincipal.isSocial()) {
            throw new OAuth2Exception(ErrorCode.NOT_SOCIAL_ACCOUNT);
        }
    }

    @GetMapping("login")
    public String login() {
        return "/fragments/contents/member/login";
    }

    @GetMapping("editPassword")
    public String editPassword(Model model) {
        model.addAttribute("memberChangePasswordRequest", new MemberChangePasswordDto());
        return "/fragments/contents/member/edit-password";
    }

    // 서비스는 request를 몰라야 한다!
    @PostMapping("editPassword")
    public String changePassword(@AuthenticationPrincipal AuthPrincipal principal, MemberChangePasswordDto memberChangePasswordDto) {
        Password beforePassword = memberChangePasswordDto.getBeforeChangePassword();
        Password afterPassword = memberChangePasswordDto.getAfterChangePassword();
        if (beforePassword.equals(afterPassword)) {
            throw new InvalidInputException(InvalidInputException.ErrorCode.IS_SAME_PASSWORD);
        }
        if (!passwordEncoder.matches(afterPassword.value(), principal.getPassword())) {
            throw new InvalidInputException(InvalidInputException.ErrorCode.OLD_PASSWORD_MISMATCH);
        }
        memberService.changeHomePagePassword(principal.getEmail(), afterPassword);
        return "fragments/contents/index";
    }

    @GetMapping("idInquiry")
    public String idInquiry() {
        return "fragments/contents/member/id-inquiry";
    }

    @PostMapping("idInquiry")
    public String idInquiry(ModelMap modelMap,
                            MemberPrivacyDto memberPrivacyDto) {
        Optional<Email> optionalEmail = memberService.inquiryId(memberPrivacyDto);

        optionalEmail.ifPresent(emailVo ->
                modelMap.addAttribute("email", emailVo.value())
        );
        return "fragments/contents/member/id-inquiry-result";
    }

    @GetMapping("pwInquiry")
    public String pwInquiry() {
        return "fragments/contents/member/pw-inquiry";
    }

    @PostMapping("pwInquiry")
    public String pwInquiry(ModelMap modelMap,
                            @ModelAttribute("email") Email memberEmail,
                            MemberPrivacyDto memberPrivacyDto) {
        boolean isSent = memberService.inquiryPw(
                memberEmail, memberPrivacyDto
        );
        modelMap.addAttribute("isSent", isSent);

        return "fragments/contents/member/pw-inquiry-result";
    }

    @GetMapping("changePassword/{Token}")
    public String changePassword(ModelMap modelMap,
                                 @PathVariable("Token") Token token) {
        boolean isAvailable = memberService.isAvailablePasswordChangeToken(token);

        modelMap.addAttribute("isAvailable", isAvailable);

        return "fragments/contents/member/change-password";
    }

    @PostMapping("changePassword")
    public String changePassword(@ModelAttribute("token") Token token,
                                 Password password) {

        memberService.changePassword(token, password);

        return "fragments/contents/member/change-password-result";
    }

    /**
     * 회원 정보 수정
     */
    @GetMapping("edit")
    public String edit(Model model) {
        MemberInfoEditDto editRequest = new MemberInfoEditDto();
        model.addAttribute("editRequest", editRequest);
        return "/fragments/contents/member/edit";
    }

    @PostMapping("edit")
    public String edit(@AuthenticationPrincipal AuthPrincipal principal, MemberInfoEditDto editDto) {
        memberService.edit(principal.getUsername(), editDto);
        return "redirect:/fragments/contents/index";
    }
}
