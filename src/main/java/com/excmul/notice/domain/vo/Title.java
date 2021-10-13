package com.excmul.notice.domain.vo;

import com.excmul.notice.exception.InvalidInputException;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Pattern;

@Embeddable
@EqualsAndHashCode
public class Title {
    @Transient
    private static final String TITLE_VALIDATOR = "^.{1,25}$";

    @Column(name = "NOTICE_TITLE")
    private String title;

    protected Title() {
    }

    public Title(String title) {
        validate(title);
        this.title = title;
    }

    public void validate(String title) {
        if (!Pattern.matches(TITLE_VALIDATOR, title)) {
            throw new InvalidInputException(InvalidInputException.ErrorCode.TITLE);
        }
    }

    @Override
    public String toString() {
        return this.value();
    }

    public String value() {
        return title;
    }
}
