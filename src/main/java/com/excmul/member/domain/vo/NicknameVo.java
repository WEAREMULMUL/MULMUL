package com.excmul.member.domain.vo;

import com.excmul.member.exception.MemberException;
import com.excmul.member.exception.MemberExceptionMessage;
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
        if (!Pattern.matches(NICKNAME_VALIDATOR, nickname)) {
            throw new MemberException(NICKNAME);
        }
    }

    @Override
    public String toString() {
        if (this.nickname() == null) {
            throw new MemberException(NICKNAME);
        }
        return this.nickname();
    }

    public String nickname() {
        return nickname;
    }
}
