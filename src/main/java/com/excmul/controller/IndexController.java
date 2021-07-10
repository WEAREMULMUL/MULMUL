package com.excmul.controller;

import com.excmul.domain.category.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class IndexController {
    private final CategoryService categoryService;

    @GetMapping({"/", ""})
    public String index() {
        return "index";
    }

    @GetMapping("/categories")
    public String categories(ModelMap modelMap) {
        modelMap.addAttribute("categoryNode", categoryService.findCategoryByLevel(0));
        return "f:categories";
    }
}

// user <<-- 동건..
// category처럼 DDD 방향????
