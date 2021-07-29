package com.excmul.member.ui;

import com.excmul.member.application.MemberService;
import com.excmul.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService userService;

    @GetMapping("/sign")
    public String sign() {
        return "sign";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // request에서 valide를 진행하는건 어떨까?
    @PostMapping("/sign") //
    public String createUser(MemberDto userDto) {

        System.out.println("------" + userDto.getUsername());

        System.out.println("------" + userDto.getPassword());

        userService.createUser(userDto);
        return "redirect:/login";
    }

}
