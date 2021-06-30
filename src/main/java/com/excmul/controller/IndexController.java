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

    @GetMapping("/sign")
    public String sign() {
        return "sign";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}

// 회원가입, 푸터 -> 외부 디자인 날리기~~
// 불변 객체 공부하기~~
// https://velog.io/@conatuseus/Java-Immutable-Object%EB%B6%88%EB%B3%80%EA%B0%9D%EC%B2%B4
