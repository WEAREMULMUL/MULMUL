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
import java.util.Objects;
import java.util.regex.Pattern;

/*
 * :: Username [class]
 *
 * 삼항연산자는 되도록 쓰지 않으려고 주의했습니다.
 *
 *
 * 해당 어노테이션을 통해 재정의가 가능한데, 어떤 방법이 좋을지 모르겠네요
 *
 */

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Username implements BaseAggregate {

    @Transient
    private static final String USERNAME_VALIDATOR = "^[가-힣]{2,10}$";

    @Column(name = "username",nullable = false)
    private String value;

    public Username(String username) {
        validate(username);
        this.value = username;
    }

    @Override
    public void validate(String username) {
        if (!Pattern.matches(USERNAME_VALIDATOR, username)) {
            throw new UserException(UserExceptionMessage.USERNAME);
        }
    }

    @Override
    public String toString() {
        if (this.getValue() == null) {
            throw new UserException(UserExceptionMessage.USERNAME);
        }
        return this.getValue();
    }

    /**
     @Override public boolean equals(Object object) {
     if (!(object instanceof Username)) {
     return false;
     }
     Username username = (Username) object;
     return this.getValue().equals(username.getValue());
     }

     @Override public int hashCode() {
     if (this.getValue() == null) {
     return HASH_CODE_NULL;
     }
     return this.getValue().hashCode();
     }
     */
}