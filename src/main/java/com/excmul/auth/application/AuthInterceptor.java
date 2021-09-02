package com.excmul.auth.application;

import com.excmul.auth.dto.AuthPrincipal;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;

public abstract class AuthInterceptor {
    protected Optional<AuthPrincipal> authPrincipal(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();

        if (principal instanceof OAuth2AuthenticationToken) {
            return ofAuthPrincipal((OAuth2AuthenticationToken) principal);
        }
        if (principal instanceof UsernamePasswordAuthenticationToken) {
            return ofAuthPrincipal((UsernamePasswordAuthenticationToken) principal);
        }
        return Optional.empty();
    }

    private Optional<AuthPrincipal> ofAuthPrincipal(OAuth2AuthenticationToken token) {
        return Optional.of(token.getPrincipal())
                .map(user -> (AuthPrincipal) user);
    }

    private Optional<AuthPrincipal> ofAuthPrincipal(UsernamePasswordAuthenticationToken token) {
        return Optional.of(token.getPrincipal())
                .map(user -> (AuthPrincipal) user);
    }
}
