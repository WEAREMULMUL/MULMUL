package com.excmul.member.domain.vo;

import com.excmul.member.exception.InvalidInputException;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;

import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Pattern;

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
        if (!StringUtils.hasText(phoneNumber) || !Pattern.matches(PHONE_NUMBER_VALIDATOR, phoneNumber)) {
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
}