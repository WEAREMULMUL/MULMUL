package com.excmul.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Objects;


@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final List<HandlerInterceptor> interceptors;

    public void addInterceptors(InterceptorRegistry registry) {
        interceptors.forEach(iInterceptor ->
                addInterceptor(registry, iInterceptor)
        );
    }

    private void addInterceptor(InterceptorRegistry registry, HandlerInterceptor interceptor) {
        Interceptor interceptorConfig = interceptor.getClass().getDeclaredAnnotation(Interceptor.class);
        if (Objects.isNull(interceptorConfig)) {
            return;
        }

        registry.addInterceptor(interceptor)
                .addPathPatterns(interceptorConfig.pathPatterns())
                .excludePathPatterns(interceptorConfig.excludePathPatterns())
                .excludePathPatterns(interceptorConfig.defaultExcludePathPatterns());
    }
}
