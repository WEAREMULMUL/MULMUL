package com.excmul.member.dto;

import com.excmul.member.domain.vo.Password;
import com.excmul.member.exception.InvalidInputException;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class MemberChangePasswordDto {
    private Password beforeChangePassword;
    private Password afterChangePassword;
    private Password afterChangeConfirmPassword;

    public Password changePassword(PasswordEncoder passwordEncoder) {
        return afterChangeConfirmPassword.encode(passwordEncoder);
    }
}