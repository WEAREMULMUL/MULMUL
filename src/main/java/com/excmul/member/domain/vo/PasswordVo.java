package com.excmul.member.domain.vo;

import com.excmul.member.exception.MemberException;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Pattern;

import static com.excmul.member.exception.MemberExceptionMessage.*;

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
        this.password = passwordEncode(password);
    }

    private String passwordEncode(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public void validate(String password) {
        if (!StringUtils.hasText(password) || !Pattern.matches(PASSWORD_VALIDATOR, password)) {
            throw new MemberException(PASSWORD);
        }
    }

    public String value() {
        return password;
    }

    @Override
    public String toString() {
        if (this.value() == null) {
            throw new MemberException(PASSWORD);
        }
        return this.value();
    }
}
