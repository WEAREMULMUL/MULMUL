package com.excmul.member.ui;

import com.excmul.member.application.MemberService;
import com.excmul.member.dto.DefaultMemberSignRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        model.addAttribute("memberSignRequest", new DefaultMemberSignRequest());
        return "fragments/contents/member/sign";
    }

    @PostMapping("/auth/sign")
    public String sign(DefaultMemberSignRequest memberSignRequest) {
        memberService.createDefaultMember(memberSignRequest);
        return "redirect:/index";
    }

    @GetMapping("/auth/login")
    public String login() {
        return "/fragments/contents/member/login";
    }
}
