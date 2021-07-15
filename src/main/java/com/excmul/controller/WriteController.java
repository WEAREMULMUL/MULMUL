package com.excmul.controller;

import com.excmul.domain.category.dto.CategoryNode;
import com.excmul.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class WriteController {
    private final CategoryService categoryService;

    @GetMapping("write")
    public String write(ModelMap modelMap) {
        CategoryNode categoryNode = categoryService.findCategoryByLevel(3);
        modelMap.addAttribute("categoryData", categoryNode.toJson());
        return "write";
    }
}
