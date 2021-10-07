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
public class Nickname {

    @Transient
    private static final String NICKNAME_VALIDATOR = "^[a-zA-Z가-힣0-9]{2,20}$";

    @Column(name = "MEMBER_NICKNAME")
    private String nickname;

    protected Nickname() {

    }

    public Nickname(String nickname) {
        validate(nickname);
        this.nickname = nickname;
    }

    public void validate(String nickname) {
        if (!Pattern.matches(NICKNAME_VALIDATOR, nickname)) {
            throw new InvalidInputException(InvalidInputException.ErrorCode.NICKNAME);
        }
    }

    @Override
    public String toString() {
        return this.value();
    }

    public String value() {
        return nickname;
    }

}
