package com.excmul.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class IndexController {
    @GetMapping("header")
    public String headerTest() {
        return "header";
    }
}
