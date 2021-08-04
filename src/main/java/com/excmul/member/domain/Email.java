package com.excmul.member.domain;

import com.excmul.common.domain.BaseAggregate;
import com.excmul.member.exception.MemberException;
import com.excmul.member.exception.MemberExceptionMessage;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


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

    @Column(name = "member_email_local_part", nullable = false)
    private String emailLocalPart;

    @Column(name = "member_email_domian_part", nullable = false)
    private String emailDomainPart;

    public Email(String email) {
        validate(email);
        divideMail(email);
    }

    private void divideMail(String email) {
        List<String> dividedEmail = splitEntireEmail(email);
        this.emailLocalPart = dividedEmail.get(LOCAL_PART_NUMBER);
        this.emailDomainPart = dividedEmail.get(DOMAIN_PART_NUMBER);
    }

    private List<String> splitEntireEmail(String email) {
        return Arrays.asList(email.split(PART_VALIDATOR));
    }

    @Override
    public void validate(String email) {
        if (!StringUtils.hasText(email) || !Pattern.matches(EMAIL_VALIDATOR, email)) {
            throw new MemberException(MemberExceptionMessage.EMAIL);
        }
    }

    @Override
    public String toString() {
        if (this.getEmailLocalPart() == null || this.getEmailDomainPart() == null) {
            throw new MemberException(MemberExceptionMessage.EMAIL);
        }
        return String.format(EMAIL_FORMAT, this.getEmailDomainPart(), this.getEmailLocalPart());
    }

    public String getEmailLocalPart() {
        return emailLocalPart;
    }

    public String getEmailDomainPart() {
        return emailDomainPart;
    }
}
