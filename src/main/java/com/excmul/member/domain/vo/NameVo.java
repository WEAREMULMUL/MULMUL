package com.excmul.member.domain.vo;

import com.excmul.member.exception.InvaildInputException;
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
        if (!StringUtils.hasText(name) || !Pattern.matches(NAME_VALIDATOR, name)) {
            throw new InvaildInputException(InvaildInputException.ErrorCode.NAME);
        }
    }

    @Override
    public String toString() {
        return this.value();
    }

    public String value() {
        return name;
    }
}
