package com.excmul.domain.user.vo;

import com.excmul.domain.common.BaseAggregate;
import com.excmul.exception.user.UserException;
import com.excmul.exception.user.UserExceptionMessage;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Pattern;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Birth implements BaseAggregate {
    // yyyymmdd :: 19970908
    @Transient
    private final static String BIRTH_VALIDATOR = "^(19[0-9][0-9]|20\\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$";

    @Column(name = "birth", nullable = false)
    private String value;

    public Birth(String birth) {
        validate(birth);
        this.value = birth;
    }

    @Override
    public void validate(String birth) {
        if (!Pattern.matches(BIRTH_VALIDATOR, birth)) {
            throw new UserException(UserExceptionMessage.BIRTH);
        }
    }

    @Override
    public String toString() {
        if (this.getValue() == null) {
            throw new UserException(UserExceptionMessage.BIRTH);
        }
        return this.getValue();
    }

}

