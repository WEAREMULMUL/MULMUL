package com.excmul.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

public interface SimpleInterceptor extends HandlerInterceptor {
    String[] getPathPatterns();
}
