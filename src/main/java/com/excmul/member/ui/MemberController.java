package com.excmul.member.ui;

import com.excmul.member.application.MemberService;
import com.excmul.member.domain.Member;
import com.excmul.member.domain.vo.AuthVo;
import com.excmul.member.domain.vo.PasswordVo;
import com.excmul.member.domain.vo.RoleVo;
import com.excmul.member.dto.MemberSignRequest;
import com.excmul.member.exception.DuplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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
}
