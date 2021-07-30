package com.excmul.product.ui;

import com.excmul.category.application.CategoryService;
import com.excmul.category.domain.CategoryNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@SuppressWarnings("SpringMVCViewInspection")
@RequestMapping("product")
@RequiredArgsConstructor
@Controller
public class ProductController {
    private final CategoryService categoryService;

    @GetMapping("list/{category}")
    public String list(@ModelAttribute("categoryNode") @Valid CategoryNode categoryNode) {
        return "product/list";
    }

    @GetMapping("write")
    public String write(ModelMap modelMap) {
        CategoryNode categoryNode = categoryService.findCategoryByLevel(3);
        modelMap.addAttribute("categoryData", categoryNode.toJson());
        return "product/write";
    }
}
