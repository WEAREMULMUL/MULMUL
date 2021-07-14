package com.excmul.domain.user.vo;

import com.excmul.domain.common.BaseAggregate;
import com.excmul.exception.user.UserException;
import com.excmul.exception.user.UserExceptionMessage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/*
 * :: Email [class]
 *
 * if문을 하나만 쓰려고 하니까, if문 자체의 코드가 너무 방대하게 길어지네요
 *
 * 정규표현식에 특수문자를 추가하고 싶은데.. 어렵네요...
 *
 *
 */

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email implements BaseAggregate {

    @Transient
    private static final String EMAIL_VALIDATOR = "^[_a-zA-Z0-9-\\+]+(\\.[_a-zA-Z0-9-]+)*@" + "[a-zA-Z0-9-]+(\\.[a-zA-Z0-9]+)*(\\.[a-zA-Z]{2,3})$";

    @Transient
    private static final String PART_VALIDATOR = "@";

    @Transient
    private static final String EMAIL_FORMAT = "%s@%s";

    @Transient
    private static final int PART_SIZE = 2;

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
        List<String> dividedEmail = Arrays.asList(email.split(PART_VALIDATOR));
        if (dividedEmail.size() != PART_SIZE) {
            throw new UserException(UserExceptionMessage.EMAIL);
        }
        return dividedEmail;
    }

    @Override
    public void validate(String email) {
        if (isNull(email) || !StringUtils.hasText(email) || !Pattern.matches(EMAIL_VALIDATOR, email)) {
            throw new UserException(UserExceptionMessage.EMAIL);
        }
    }

    @Override
    public String toString() {
        if (isNull(this.getEmailLocalPart()) || isNull(this.getEmailDomainPart())) {
            throw new UserException(UserExceptionMessage.EMAIL);
        }
        return String.format(EMAIL_FORMAT, this.getEmailDomainPart(), this.getEmailLocalPart());
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Email)) {
            return false;
        }
        Email email = (Email) object;
        return this.getEmailLocalPart().equals(email.getEmailLocalPart()) && this.getEmailDomainPart().equals(email.getEmailDomainPart());
    }

    @Override
    public int hashCode() {
        if (isNull(this.getEmailLocalPart()) || isNull(this.getEmailDomainPart())) {
            return HASH_CODE_NULL;
        }
        return HASH_CODE_PRIME * this.getEmailLocalPart().hashCode() + this.getEmailDomainPart().hashCode();
    }

    @Override
    public boolean isNull(String email) {
        return Objects.isNull(email);
    }
}
