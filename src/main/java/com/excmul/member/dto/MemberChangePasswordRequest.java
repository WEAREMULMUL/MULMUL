package com.excmul.member.dto;

import com.excmul.member.domain.vo.*;
import com.excmul.member.exception.InvalidInputException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Setter
@Getter
@NoArgsConstructor
public class MemberChangePasswordRequest {
    private PasswordVo beforeChangePassword;
    private PasswordVo afterChangePassword;
    private PasswordVo afterChangeConfirmPassword;


    public void validExistsPassword(PasswordEncoder passwordEncoder, PasswordVo authPrincipalPassword) {
        if (!passwordEncoder.matches(beforeChangePassword.value(), authPrincipalPassword.value())) {
            throw new InvalidInputException(InvalidInputException.ErrorCode.OLD_PASSWORD_MISMATCH);
        }
    }

    public void validAfterChangePasswords() {
        if (!afterChangePassword.equals(afterChangeConfirmPassword)) {
            throw new InvalidInputException(InvalidInputException.ErrorCode.NEW_PASSWORD_MISMATCH);
        }
    }

    public void validIsDifferentPassword() {
        if (beforeChangePassword.equals(afterChangePassword)) {
            throw new InvalidInputException(InvalidInputException.ErrorCode.IS_SAME_PASSWORD);
        }
    }

    public PasswordVo changePassword(PasswordEncoder passwordEncoder) {
        return afterChangeConfirmPassword.encode(passwordEncoder);
    }
}