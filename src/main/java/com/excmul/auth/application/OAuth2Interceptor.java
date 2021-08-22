package com.excmul.auth.application;

import com.excmul.auth.dto.AuthPrincipal;
import com.excmul.auth.exception.OAuth2Exception;
import com.excmul.config.Interceptor;
import com.excmul.member.domain.MemberRepository;
import com.excmul.member.exception.NotFoundMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

@Interceptor(
        pathPatterns = "/**",
        excludePathPatterns = { "/auth/**" }
)
@RequiredArgsConstructor
public class OAuth2Interceptor implements HandlerInterceptor {
    private static final String SOCIAL_LOGIN_CONTINUE_URL_PATH = "/auth/signup/social";

    private final MemberRepository memberRepository;

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                            @Nullable ModelAndView modelAndView) throws IOException {
        Optional<AuthPrincipal> optionalAuthPrincipal = oAuth2Principal(request);

        boolean isNotCompletedSignUp =
                optionalAuthPrincipal
                        .map(this::isNotCompletedSignUp)
                        .orElse(false);

        if (isNotCompletedSignUp) {
            response.sendRedirect(SOCIAL_LOGIN_CONTINUE_URL_PATH);
        }
    }

    private Optional<AuthPrincipal> oAuth2Principal(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (!(principal instanceof OAuth2AuthenticationToken)) {
            return Optional.empty();
        }
        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) principal;

        return Optional.of(oAuth2AuthenticationToken.getPrincipal())
                .map(p -> (AuthPrincipal) p);
    }

    private boolean isNotCompletedSignUp(AuthPrincipal authPrincipal) {
        return memberRepository.findById(authPrincipal.getId())
                .orElseThrow(NotFoundMemberException::new)
                .isNotCompletedSingUp();
    }
}
