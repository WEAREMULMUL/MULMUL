package com.excmul.domain.user.vo;

import com.excmul.domain.common.BaseAggregate;
import com.excmul.exception.user.UserException;
import com.excmul.exception.user.UserExceptionMessage;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/*
 * :: Email [class]
 *
 *
 *
 */

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email implements BaseAggregate {

    @Transient
    private static final String EMAIL_VALIDATOR = "^[_a-zA-Z0-9-\\+]+(\\.[_a-zA-Z0-9-]+)*@" + "[a-zA-Z0-9-]+(\\.[a-zA-Z0-9]+)*(\\.[a-zA-Z]{2,3})$";

    @Transient
    private static final String PART_VALIDATOR = "@";

    @Transient
    private static final String EMAIL_FORMAT = "%s@%s";

    @Transient
    private static final int LOCAL_PART_NUMBER = 0;

    @Transient
    private static final int DOMAIN_PART_NUMBER = 1;

    @Column(nullable = false)
    private String emailLocalPart;

    @Column(nullable = false)
    private String emailDomainPart;

    public Email(String email) {
        validate(email);

        List<String> dividedEmail = split(email);
        this.emailLocalPart = dividedEmail.get(LOCAL_PART_NUMBER);
        this.emailDomainPart = dividedEmail.get(DOMAIN_PART_NUMBER);
    }

    private List<String> split(String email) {
        return Arrays.asList(email.split(PART_VALIDATOR));
    }

    @Override
    public void validate(String email) {
        if (email == null || !StringUtils.hasText(email) || !Pattern.matches(EMAIL_VALIDATOR, email)) {
            throw new UserException(UserExceptionMessage.EMAIL);
        }
    }

    @Override
    public String toString() {
        if (this.getEmailLocalPart() == null || this.getEmailDomainPart() == null) {
            throw new UserException(UserExceptionMessage.EMAIL);
        }
        return String.format(EMAIL_FORMAT, this.getEmailDomainPart(), this.getEmailLocalPart());
    }
}
