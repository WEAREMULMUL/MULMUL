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
public class NicknameVo {

    @Transient
    private static final String NICKNAME_VALIDATOR = "^[a-zA-Z가-힣0-9]{2,20}$";

    @Column(name = "MEMBER_NICKNAME", nullable = false)
    private String nickname;

    public NicknameVo(String nickname) {
        validate(nickname);
        this.nickname = nickname;
    }

    public void validate(String nickname) {
        if (!StringUtils.hasText(nickname) || !Pattern.matches(NICKNAME_VALIDATOR, nickname)) {
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
