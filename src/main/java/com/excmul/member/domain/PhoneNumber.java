package com.excmul.member.domain;

import com.excmul.common.domain.BaseAggregate;
import com.excmul.member.exception.MemberException;
import com.excmul.member.exception.MemberExceptionMessage;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Pattern;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhoneNumber implements BaseAggregate {
    // Mobile Phone Identification Number :: 010
    @Transient
    private static final String PHONE_NUMBER_VALIDATOR = "^010[0-9]{8}$";

    @Column(name = "member_phone_number", nullable = false)
    private String value;

    public PhoneNumber(String phoneNumber) {
        validate(phoneNumber);
        this.value = phoneNumber;
    }

    @Override
    public void validate(String phoneNumber) {
        if (!Pattern.matches(PHONE_NUMBER_VALIDATOR, phoneNumber)) {
            throw new MemberException(MemberExceptionMessage.PHONE_NUMBER);
        }
    }

    @Override
    public String toString() {
        if (this.getValue() == null) {
            throw new MemberException(MemberExceptionMessage.PHONE_NUMBER);
        }
        return this.getValue();
    }

    public String getValue() {
        return value;
    }
}