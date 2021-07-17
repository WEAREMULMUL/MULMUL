package com.excmul.domain.user.vo;

import com.excmul.domain.common.BaseAggregate;
import com.excmul.exception.user.UserException;
import com.excmul.exception.user.UserExceptionMessage;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Pattern;


/* :: Password [class]
 *
 */

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password implements BaseAggregate {

    /**
     * 최소 8자 ~ 최대 30자
     * 최소 영문 소문자 1자
     * 최소 영문 대문자 1자
     * 최소 특수문자 1자 :: ~!@#$%^&*()_+`\-=\[\]\{\};':\",./<>?
     * ^(?=.*[~!@#$%^&*()_+`\-=\[\]\{\};':\",./<>?])(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])\S{8,30}$
     */
    @Transient
    private static final String PASSWORD_VALIDATOR = "^(?=.*[~!@#$%^&*()_+`\\-=\\[\\]\\{\\};':\\\",./<>?])(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])\\S{8,30}$";

    @Column(name = "password", nullable = false)
    private String value;

    public Password(PasswordEncoder encoder, String password) {
        validate(password);
        this.value = encoder.encode(password);
    }

    @Override
    public void validate(String password) {
        if (!StringUtils.hasText(password) || !Pattern.matches(PASSWORD_VALIDATOR, password)) {
            throw new UserException(UserExceptionMessage.PASSWORD);
        }

    }

    @Override
    public String toString() {
        if (this.getValue() == null) {
            throw new UserException(UserExceptionMessage.PASSWORD);
        }
        return this.getValue();
    }
}
