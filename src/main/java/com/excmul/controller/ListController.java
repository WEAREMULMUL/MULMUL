package com.excmul.controller;

import com.excmul.domain.category.CategoryCode;
import com.excmul.request.ProductPageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ListController {
    @GetMapping("list/{category}")
    public String list(@ModelAttribute("request") @Valid ProductPageRequest request, ModelAndView modelAndView) {
        return "list";
    }
}
