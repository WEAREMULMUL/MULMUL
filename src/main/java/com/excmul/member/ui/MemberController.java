package com.excmul.member.ui;

import com.excmul.auth.dto.AuthPrincipal;
import com.excmul.auth.exception.OAuth2Exception;
import com.excmul.common.domain.vo.TokenSerial;
import com.excmul.common.dto.AbstractFile;
import com.excmul.common.dto.ImageFile;
import com.excmul.member.application.MemberService;
import com.excmul.member.application.PasswordChangeTokenService;
import com.excmul.member.domain.vo.Email;
import com.excmul.member.domain.vo.Password;
import com.excmul.member.dto.*;
import com.excmul.member.exception.DuplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

import static com.excmul.auth.exception.OAuth2Exception.ErrorCode;

@RequestMapping("auth")
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final PasswordChangeTokenService passwordChangeTokenService;

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

    // 세션 유진
    @GetMapping("editPassword")
    public String editPassword(Model model) {
        model.addAttribute("memberChangePasswordRequest", new MemberChangePasswordDto());
        return "/fragments/contents/member/edit-password";
    }

    @PostMapping("editPassword")
    public String changePassword(@AuthenticationPrincipal AuthPrincipal principal, MemberChangePasswordDto request) {
        // request.getBeforeChangePassword() -> 서비스로 던져줘야
        // 서비스는 request를 몰라야 한다!
        memberService.changeHomePagePassword(principal, request);
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
                                 @PathVariable("Token") TokenSerial token) {
        boolean isAvailable = passwordChangeTokenService.isAvailablePasswordChangeToken(token);

        modelMap.addAttribute("isAvailable", isAvailable);

        return "fragments/contents/member/change-password";
    }

    @PostMapping("changePassword")
    public String changePassword(@ModelAttribute("token") TokenSerial token,
                                 Password password) {

        passwordChangeTokenService.changePassword(token, password);

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

    /**
     * 회원 탈퇴
     */
    @GetMapping("leaveId")
    public String leaveId() {
        return "/fragments/contents/member/leaveId";
    }

    @PostMapping("leaveId")
    public String leaveId(@AuthenticationPrincipal AuthPrincipal principal,
                          final HttpSession httpSession) {
        long memberId = principal.getId();
        memberService.leaveId(memberId);

        httpSession.invalidate();

        return "/fragments/contents/member/leaveId-result";
    }

    /**
     * 프로필 사진
     */
    @GetMapping("profile")
    public String profile(@AuthenticationPrincipal AuthPrincipal principal,
                          Model model) {
        String profileUrl = memberService.getProfileUrl(principal.getId());
        model.addAttribute("profileUrl", profileUrl);
        return "/fragments/contents/member/profile";
    }

    @PostMapping("profile")
    public String profile(@AuthenticationPrincipal AuthPrincipal principal,
                          @RequestPart(value = "profile") MultipartFile profile) throws IOException {
        if (profile.isEmpty()) {
            throw new RuntimeException("프로필 사진이 없습니다.");
        }

        ImageFile imageFile = new ImageFile(profile);
        imageFile.saveFileToLocal();
        memberService.updateProfileUrl(principal.getId(), imageFile.getUniqueFileName());
        return "redirect:/auth/profile";
    }
}
