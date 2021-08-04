package com.excmul.member.ui;

import com.excmul.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;


@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService userService;


}
