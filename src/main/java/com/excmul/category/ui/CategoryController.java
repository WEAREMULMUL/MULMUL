package com.excmul.category.ui;

import com.excmul.category.application.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("category")
@RequiredArgsConstructor
@Controller
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping({"", "/" })
    public String categories(ModelMap modelMap) {
        modelMap.addAttribute("categoryNode", categoryService.findCategoryByLevel(0));
        return "f:category/list";
    }

    @GetMapping("select")
    public String selectCategory(ModelMap modelMap) {
        modelMap.addAttribute("categoryNode", categoryService.findCategoryByLevel(0));
        return "category/select";
    }
}
