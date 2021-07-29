package com.excmul.member.domain;

import com.excmul.common.domain.BaseAggregate;
import com.excmul.member.exception.MemberException;
import com.excmul.member.exception.MemberExceptionMessage;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Pattern;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password implements BaseAggregate {

    /**
     * 최소 8자 ~ 최대 30자
     * 최소 영문자 1자
     * 최소 숫자 1자
     * 최소 특수문자 1자 :: $@$!%*#?&
     * ^(?=.*[~!@#$%^&*()_+`\-=\[\]\{\};':\",.\<>?])(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])\S{8,30}$
     */
    @Transient
    private static final String PASSWORD_VALIDATOR = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[가-힣A-Za-z\\d$@$!%*#?&]{8,30}$";

    @Column(name = "member_password", nullable = false)
    private String value;

    public Password(String password) {
        validate(password);
        this.value = passwordEncode(password);
    }

    private String passwordEncode(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public void validate(String password) {
        if (!StringUtils.hasText(password) || !Pattern.matches(PASSWORD_VALIDATOR, password)) {
            throw new MemberException(MemberExceptionMessage.PASSWORD);
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        if (this.getValue() == null) {
            throw new MemberException(MemberExceptionMessage.PASSWORD);
        }
        return this.getValue();
    }
}
