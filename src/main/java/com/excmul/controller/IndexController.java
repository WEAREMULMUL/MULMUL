package com.excmul.controller;

import com.excmul.domain.category.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class IndexController {
    private final CategoryService categoryService;

    @GetMapping({"/", ""})
    public String index() {
        return "layout";
    }

    @GetMapping("/categories")
    public String categories(ModelAndView modelAndView) {
        modelAndView.addObject("categoryNode", categoryService.findCategoryByLevel(0));
        return "categories";
    }

    @GetMapping("/sign")
    public String sign() {
        return "sign";
    }

}
