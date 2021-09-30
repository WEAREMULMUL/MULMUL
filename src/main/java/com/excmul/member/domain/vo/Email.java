package com.excmul.member.domain.vo;

import com.excmul.member.exception.InvalidInputException;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Pattern;

@ToString
@Embeddable
@EqualsAndHashCode
public class Email {
    @Transient
    private static final String EMAIL_VALIDATOR = "^[_a-zA-Z0-9-\\+]+(\\.[_a-zA-Z0-9-]+)*@" + "[a-zA-Z0-9-]+(\\.[a-zA-Z0-9]+)*(\\.[a-zA-Z]{2,3})$";

    @Transient
    private static final String EMAIL_FORMAT = "%s@%s";

    @Column(name = "MEMBER_EMAIL", nullable = false)
    private String email;

    protected Email() {

    }

    public Email(String email) {
        validate(email);
        this.email = email;
    }

    public void validate(String email) {
        if (!StringUtils.hasText(email) || !Pattern.matches(EMAIL_VALIDATOR, email)) {
            throw new InvalidInputException(InvalidInputException.ErrorCode.EMAIL);
        }
    }

    public String value() {
        return email;
    }
}
