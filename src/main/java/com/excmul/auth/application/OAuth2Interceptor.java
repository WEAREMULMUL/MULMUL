package com.excmul.auth.application;

import com.excmul.auth.dto.AuthPrincipal;
import com.excmul.common.domain.Url;
import com.excmul.config.Interceptor;
import com.excmul.member.domain.MemberRepository;
import com.excmul.member.exception.NotFoundMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Interceptor(
        pathPatterns = "/**",
        excludePathPatterns = { "/auth/**" }
)
@RequiredArgsConstructor
public class OAuth2Interceptor extends AuthInterceptor implements HandlerInterceptor {
    private static final Url SOCIAL_SIGN_UP_URL =
            Url.siteUrl()
                    .append("auth")
                    .append("signup")
                    .append("social");

    private final MemberRepository memberRepository;

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                            @Nullable ModelAndView modelAndView) throws IOException {
        Optional<AuthPrincipal> optionalAuthPrincipal = super.authPrincipal(request);

        boolean isNotCompletedSignUp =
                optionalAuthPrincipal
                        .map(this::isNotCompletedSignUp)
                        .orElse(false);

        if (isNotCompletedSignUp) {
            response.sendRedirect(SOCIAL_SIGN_UP_URL.value());
        }
    }

    // Basic -> 레파지토리에 요청 X
    // OAuth2 -> 레파지토리에 요청 O
    private boolean isNotCompletedSignUp(AuthPrincipal authPrincipal) {
        if (!authPrincipal.isSocial()) {
            return false;
        }
        return memberRepository.findById(authPrincipal.getId())
                .orElseThrow(NotFoundMemberException::new)
                .isNotCompletedSingUp();
    }
}
