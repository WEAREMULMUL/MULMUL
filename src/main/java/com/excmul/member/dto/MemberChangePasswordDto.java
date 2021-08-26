package com.excmul.member.dto;

import com.excmul.member.domain.vo.Password;
import com.excmul.member.exception.InvalidInputException;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class MemberChangePasswordDto {
    private Password beforeChangePassword;
    private Password afterChangePassword;
    private Password afterChangeConfirmPassword; // 프론트 validate..!

    // dto는 dto의 역할만
    public void validExistsPassword(PasswordEncoder passwordEncoder, Password authPrincipalPassword) {
        if (!passwordEncoder.matches(beforeChangePassword.value(), authPrincipalPassword.value())) {
            throw new InvalidInputException(InvalidInputException.ErrorCode.OLD_PASSWORD_MISMATCH);
        }
    }

    // dto는 dto의 역할만
    public void validAfterChangePasswords() {
        if (!afterChangePassword.equals(afterChangeConfirmPassword)) {
            throw new InvalidInputException(InvalidInputException.ErrorCode.NEW_PASSWORD_MISMATCH);
        }
    }

    // dto는 dto의 역할만
    public void validIsDifferentPassword() {
        if (beforeChangePassword.equals(afterChangePassword)) {
            throw new InvalidInputException(InvalidInputException.ErrorCode.IS_SAME_PASSWORD);
        }
    }

    public Password changePassword(PasswordEncoder passwordEncoder) {
        return afterChangeConfirmPassword.encode(passwordEncoder);
    }
}