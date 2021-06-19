package com.excmul.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class IndexController {
    @GetMapping({"/", ""})
    public String index() {
        return "layout";
    }
}

// user <<-- 동건..
// category처럼 DDD 방향????
