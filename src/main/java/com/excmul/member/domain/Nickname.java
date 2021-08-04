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
public class Nickname implements BaseAggregate {

    @Transient
    private static final String NICKNAME_VALIDATOR = "^[a-zA-Z가-힣0-9]{2,20}$";

    @Column(nullable = false)
    private String value;

    public Nickname(String nickname) {
        validate(nickname);
        this.value = nickname;
    }

    @Override
    public void validate(String nickname) {
        if (!Pattern.matches(NICKNAME_VALIDATOR, nickname)) {
            throw new MemberException(MemberExceptionMessage.NICKNAME);
        }
    }

    @Override
    public String toString() {
        if (this.getValue() == null) {
            throw new MemberException(MemberExceptionMessage.NICKNAME);
        }
        return this.getValue();
    }

    public String getValue() {
        return value;
    }
}
