package com.excmul.member.domain.vo;

import com.excmul.member.exception.InvalidInputException;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Pattern;


@Embeddable
@EqualsAndHashCode
public class Name {
    @Transient
    private static final String NAME_VALIDATOR = "^[가-힣]{2,10}$";

    @Column(name = "MEMBER_NAME")
    private String name;

    protected Name() {

    }

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    public void validate(String name) {
        if (!Pattern.matches(NAME_VALIDATOR, name)) {
            throw new InvalidInputException(InvalidInputException.ErrorCode.NAME);
        }
    }

    @Override
    public String toString() {
        return this.value();
    }

    public String value() {
        return name;
    }

}
