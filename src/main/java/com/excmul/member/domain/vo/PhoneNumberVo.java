package com.excmul.member.domain.vo;

import com.excmul.member.exception.MemberException;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Pattern;

import static com.excmul.member.exception.MemberExceptionMessage.*;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhoneNumberVo {
    // :: Mobile Phone Identification Number :: 010
    @Transient
    private static final String PHONE_NUMBER_VALIDATOR = "^010[0-9]{8}$";

    @Column(name = "MEMBER_PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    public PhoneNumberVo(String phoneNumber) {
        validate(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    public void validate(String phoneNumber) {
        if (!Pattern.matches(PHONE_NUMBER_VALIDATOR, phoneNumber)) {
            throw new MemberException(PHONE_NUMBER);
        }
    }

    @Override
    public String toString() {
        if (this.value() == null) {
            throw new MemberException(PHONE_NUMBER);
        }
        return this.value();
    }

    public String value() {
        return phoneNumber;
    }
}