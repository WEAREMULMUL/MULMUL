package com.excmul.member.application;

import com.excmul.common.domain.vo.TokenSerial;
import com.excmul.common.exception.TokenException;
import com.excmul.member.domain.PasswordChangeToken;
import com.excmul.member.domain.PasswordChangeTokenRepository;
import com.excmul.member.domain.vo.Password;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PasswordChangeTokenService {
    private final PasswordEncoder passwordEncoder;

    private final PasswordChangeTokenRepository passwordChangeTokenRepository;

    @Transactional(readOnly = true)
    public boolean isAvailablePasswordChangeToken(TokenSerial token) {
        PasswordChangeToken passwordChangeToken = passwordChangeTokenRepository.findByTokenSerial(token)
                .orElseThrow(() -> new TokenException(TokenException.ErrorCode.NOT_FOUND));

        return passwordChangeToken.isAvailable();
    }

    @Transactional
    public void changePassword(TokenSerial token, Password password) {
        Password encodedPassword = password.encode(passwordEncoder);

        PasswordChangeToken passwordChangeToken = passwordChangeTokenRepository.findByTokenSerial(token)
                .orElseThrow(() -> new TokenException(TokenException.ErrorCode.NOT_FOUND));

        passwordChangeToken.use(encodedPassword);
    }
}
