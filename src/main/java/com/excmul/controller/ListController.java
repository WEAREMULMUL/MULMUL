package com.excmul.controller;

import com.excmul.request.ProductPageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.Valid;

@Controller
public class ListController {
    @GetMapping("list/{category}")
    public String list(@ModelAttribute("request") @Valid ProductPageRequest request) {
        return "list";
    }
}
