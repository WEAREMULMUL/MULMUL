package com.excmul.member.ui;

import com.excmul.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService userService;

    @RequestMapping({"", "/", "index"})
    public String index() {
        return "fragments/contents/index";
    }
}
