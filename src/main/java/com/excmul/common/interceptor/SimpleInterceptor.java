package com.excmul.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

public interface SimpleInterceptor extends HandlerInterceptor {
    String[] includePathPatterns();

    String[] excludePathPatterns();
}
