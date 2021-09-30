package com.excmul.member.domain.vo;

import com.excmul.member.exception.InvalidInputException;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Pattern;

@Embeddable
@EqualsAndHashCode
public class PhoneNumber {
    // :: Mobile Phone Identification Number :: 010
    @Transient
    private static final String PHONE_NUMBER_VALIDATOR = "^010[0-9]{8}$";

    @Column(name = "MEMBER_PHONE_NUMBER")
    private String phoneNumber;

    public PhoneNumber(String phoneNumber) {
        validate(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    public void validate(String phoneNumber) {
        if (!Pattern.matches(PHONE_NUMBER_VALIDATOR, phoneNumber)) {
            throw new InvalidInputException(InvalidInputException.ErrorCode.PHONE_NUMBER);
        }
    }

    @Override
    public String toString() {
        return this.value();
    }

    public String value() {
        return phoneNumber;
    }

    protected PhoneNumber() {

    }
}