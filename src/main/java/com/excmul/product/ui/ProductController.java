package com.excmul.product.ui;

import com.excmul.category.application.CategoryService;
import com.excmul.category.domain.CategoryCode;
import com.excmul.category.domain.CategoryNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@SuppressWarnings("SpringMVCViewInspection")
@RequestMapping("product")
@RequiredArgsConstructor
@Controller
public class ProductController {
    private final CategoryService categoryService;

    @GetMapping("list/{categoryCode}")
    public String list(ModelMap modelMap,
                       @PathVariable("categoryCode") CategoryCode categoryCode) {
        modelMap.addAttribute("categoryMap", categoryService.findCategoryNodeByCode(categoryCode));
        return "product/list";
    }

    @GetMapping("write")
    public String write(ModelMap modelMap) {
        return "product/write";
    }
}
