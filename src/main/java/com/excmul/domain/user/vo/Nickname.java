package com.excmul.domain.user.vo;

import com.excmul.domain.common.BaseAggregate;
import com.excmul.exception.user.UserException;
import com.excmul.exception.user.UserExceptionMessage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.Objects;
import java.util.regex.Pattern;

/*
 * :: Nickname [class]
 *
 *
 * 정규표현식에 특수문자를 추가하고 싶은데.. 어렵네요...
 *
 *
 *
 */

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Nickname implements BaseAggregate {

    @Transient
    private static final String NICKNAME_VALIDATOR = "^[a-zA-Z가-힣0-9]{2,20}$";

    @Column(nullable = false)
    private String value;

    @Override
    public void validate(String nickname) {
        if (!Pattern.matches(NICKNAME_VALIDATOR, nickname)) {
            throw new UserException(UserExceptionMessage.NICKNAME);
        }
    }

    @Override
    public String toString() {
        if (isNull(this.getValue())) {
            throw new UserException(UserExceptionMessage.NICKNAME);
        }
        return this.getValue();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Nickname)) {
            return false;
        }
        Nickname nickname = (Nickname) object;
        return this.getValue().equals(nickname.getValue());
    }

    @Override
    public int hashCode() {
        if (isNull(this.getValue())) {
            return HASH_CODE_NULL;
        }
        return this.getValue().hashCode();
    }

    @Override
    public boolean isNull(String nickname) {
        return Objects.isNull(nickname);
    }
}
