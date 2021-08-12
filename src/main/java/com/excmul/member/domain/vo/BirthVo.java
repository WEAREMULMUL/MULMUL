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
public class BirthVo {
    /**
     * yyyymmdd :: 19970908
     */
    @Transient
    private final static String BIRTH_VALIDATOR = "^(19[0-9][0-9]|20\\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$";

    @Column(name = "MEMBER_BIRTH", nullable = false)
    private String birth;

    public BirthVo(String birth) {
        validate(birth);
        this.birth = birth;
    }

    public void validate(String birth) {
        if (!Pattern.matches(BIRTH_VALIDATOR, birth)) {
            throw new MemberException(BIRTH);
        }
    }

    @Override
    public String toString() {
        if (this.value() == null) {
            throw new MemberException(BIRTH);
        }
        return this.value();
    }

    public String value() {
        return birth;
    }

}

