package com.excmul.member.domain.vo;

<<<<<<< HEAD
import com.excmul.member.exception.InvalidInputException;
=======
import com.excmul.auth.application.AuthorizationException;
import com.excmul.member.exception.MemberException;
import com.excmul.member.exception.MemberExceptionMessage;
>>>>>>> custom-security/donggeon
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Pattern;


@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PasswordVo {

    /**
     * 최소 8자 ~ 최대 30자
     * 최소 영문자 1자
     * 최소 숫자 1자
     * 최소 특수문자 1자 :: $@$!%*#?&
     * ^(?=.*[~!@#$%^&*()_+`\-=\[\]\{\};':\",.\<>?])(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])\S{8,30}$
     */
    @Transient
    private static final String PASSWORD_VALIDATOR = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[가-힣A-Za-z\\d$@$!%*#?&]{8,30}$";

    @Column(name = "MEMBER_PASSWORD", nullable = false)
    private String password;

    public PasswordVo(String password) {
        validate(password);
        this.password = password;
    }

    private PasswordVo(PasswordEncoder passwordEncoder, String password) {
        String encodedPassword = passwordEncoder.encode(password);

        this.password = encodedPassword;
    }

    public PasswordVo encode(PasswordEncoder passwordEncoder) {
        return new PasswordVo(passwordEncoder, password);
    }

    public void validate(String password) {
        if (!StringUtils.hasText(password) || !Pattern.matches(PASSWORD_VALIDATOR, password)) {
            throw new InvalidInputException(InvalidInputException.ErrorCode.PASSWORD);
        }
    }

    public String value() {
        return password;
    }

    @Override
    public String toString() {
        return this.value();
    }

    public void checkPassword(String password) {
        if (!StringUtils.equals(this.password, password)) {
            throw new AuthorizationException();
        }
    }
}
