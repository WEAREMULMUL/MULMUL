package com.excmul.config;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Indexed
@Component
public @interface Interceptor {
    String[] pathPatterns();

    String[] excludePathPatterns() default "";

    String[] defaultExcludePathPatterns() default { "/auth/**", "/js/**", "/css/**", "/image/**", "/error" };
}
