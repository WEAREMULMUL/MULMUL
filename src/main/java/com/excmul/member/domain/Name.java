package com.excmul.member.domain;

import com.excmul.common.domain.BaseAggregate;
import com.excmul.member.exception.MemberException;
import com.excmul.member.exception.MemberExceptionMessage;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Pattern;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Name implements BaseAggregate {

    @Transient
    private static final String NAME_VALIDATOR = "^[가-힣]{2,10}$";

    @Column(nullable = false)
    private String value;

    public Name(String name) {
        validate(name);
        this.value = name;
    }

    @Override
    public void validate(String name) {
        if (!Pattern.matches(NAME_VALIDATOR, name)) {
            throw new MemberException(MemberExceptionMessage.NAME);
        }
    }

    @Override
    public String toString() {
        if (this.getValue() == null) {
            throw new MemberException(MemberExceptionMessage.NAME);
        }
        return this.getValue();
    }

    public String getValue() {
        return value;
    }
}
