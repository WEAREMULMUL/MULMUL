package com.excmul.controller;

import com.excmul.domain.category.dto.CategoryNode;
import com.excmul.domain.category.repository.CategoryRepository;
import com.excmul.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("modal")
@Controller
public class ModalController {
    private final CategoryService categoryService;

    @GetMapping("select-category")
    public String selectCategory(ModelMap modelMap) {
        CategoryNode categoryNode = categoryService.findCategoryByLevel(3);
        modelMap.addAttribute("categoryData", categoryNode.toJson());
        return "select-category";
    }

    @GetMapping("select-area")
    public String selectArea(ModelMap modelMap) {
        return "select-area";
    }
}
