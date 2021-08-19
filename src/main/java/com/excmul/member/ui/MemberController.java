package com.excmul.member.ui;

import com.excmul.auth.AuthPrincipal;
import com.excmul.member.application.MemberService;
<<<<<<< HEAD
import com.excmul.member.dto.MemberChangePasswordRequest;
import com.excmul.member.dto.MemberSignRequest;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


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
        memberService.changePassword(principal.loginMember(), request);
        return "fragments/contents/index";
    }
    
}
