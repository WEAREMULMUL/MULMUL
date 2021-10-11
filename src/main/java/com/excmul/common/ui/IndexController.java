package com.excmul.common.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"", "/", "index"})
public class IndexController {
    @GetMapping
    public String index() {
        return "fragments/contents/index";
    }
}