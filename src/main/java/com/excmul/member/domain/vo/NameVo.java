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
public class NameVo {

    @Transient
    private static final String NAME_VALIDATOR = "^[가-힣]{2,10}$";

    @Column(name = "MEMBER_NAME", nullable = false)
    private String name;

    public NameVo(String name) {
        validate(name);
        this.name = name;
    }

    public void validate(String name) {
        if (!Pattern.matches(NAME_VALIDATOR, name)) {
            throw new MemberException(NAME);
        }
    }

    @Override
    public String toString() {
        if (this.name() == null) {
            throw new MemberException(NAME);
        }
        return this.name();
    }

    public String name() {
        return name;
    }
}
