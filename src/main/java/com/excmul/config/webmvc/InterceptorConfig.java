package com.excmul.config.webmvc;

import com.excmul.interceptor.SimpleInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    private final List<SimpleInterceptor> interceptors;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        for (SimpleInterceptor iInterceptor : interceptors) {
            registry.addInterceptor(iInterceptor)
                    .addPathPatterns(iInterceptor.includePathPatterns())
                    .excludePathPatterns(iInterceptor.excludePathPatterns());
        }
    }
}
