package com.excmul.auth.config;

import com.excmul.auth.application.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthService authPrincipalService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // TODO Auto-generated method stub
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(authPrincipalService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable();

        http
                .authorizeRequests()
                .antMatchers("/index", "/", "/auth/**", "/js/**", "/css/**", "/image/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling();

        http
                .formLogin()
                .loginPage("/auth/login")
                .loginProcessingUrl("/auth/loginProc")
                .defaultSuccessUrl("/index")
                .and()
                .exceptionHandling();

        http
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/index")
                .invalidateHttpSession(true)
                .and()
                .exceptionHandling();

        configureOAuth2(http);
    }

    private void configureOAuth2(HttpSecurity http) throws Exception {
        http
                .oauth2Login()
                .userInfoEndpoint()
                .userService(authPrincipalService);
    }
}
