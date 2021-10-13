package com.excmul.member.domain.vo;

import com.excmul.member.exception.InvalidInputException;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Pattern;

@Embeddable
@EqualsAndHashCode
public class Birth {
    /**
     * yyyymmdd :: 19970908
     */
    @Transient
    private final static String BIRTH_VALIDATOR = "^(19[0-9][0-9]|20\\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$";

    @Column(name = "MEMBER_BIRTH")
    private String birth;

    protected Birth() {

    }

    public Birth(String birth) {
        validate(birth);
        this.birth = birth;
    }

    public void validate(String birth) {
        if (!Pattern.matches(BIRTH_VALIDATOR, birth)) {
            throw new InvalidInputException(InvalidInputException.ErrorCode.BIRTH);
        }
    }

    @Override
    public String toString() {
        return this.value();
    }

    public String value() {
        return birth;
    }

}

