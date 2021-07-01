package com.excmul.interceptor;

import com.excmul.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Component
public class HeaderCategoryInterceptor implements SimpleInterceptor {
    private final CategoryService categoryService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) {
        if (modelAndView == null)
            return;
        modelAndView.addObject("categoryNode", categoryService.findCategoryByLevel(1));
    }

    @Override
    public String[] includePathPatterns() {
        return new String[] { "/**" };
    }

    @Override
    public String[] excludePathPatterns() {
        return new String[] { "/assets/**"};
    }
}
