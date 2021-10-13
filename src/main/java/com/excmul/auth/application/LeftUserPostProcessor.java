package com.excmul.auth.application;

import com.excmul.auth.dto.AuthPrincipal;
import com.excmul.member.exception.MemberLeftException;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class LeftUserPostProcessor implements LoginPostProcessor {
    @SneakyThrows
    @Override
    public void process(final AuthPrincipal authPrincipal) {
        if (authPrincipal.isLeft()) {
            throw new MemberLeftException();
        }
    }
}
