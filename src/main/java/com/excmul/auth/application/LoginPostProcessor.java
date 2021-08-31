package com.excmul.auth.application;

import com.excmul.auth.dto.AuthPrincipal;

public interface LoginPostProcessor {
    void process(final AuthPrincipal authPrincipal);
}
