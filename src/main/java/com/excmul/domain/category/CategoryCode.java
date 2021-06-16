package com.excmul.domain.category;

import com.excmul.util.StringUtils;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Access(AccessType.FIELD)
public class CategoryCode implements Serializable {
    private final String code;

    public CategoryCode(CategoryCode parentCode) {
        this.code = parentCode + newCodeValue();
    }

    public CategoryCode() {
        this.code = newCodeValue();
    }

    private String newCodeValue() {
        return StringUtils.randomString(2);
    }

    public String getValue() {
        return code;
    }

    @Override
    public String toString() {
        return code;
    }
}
