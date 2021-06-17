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
        this.code = (parentCode == null ? "" : parentCode) + newCodeValue();
    }

    public CategoryCode() {
        this.code = newCodeValue();
    }

    private CategoryCode(String codeValue) {
        this.code = codeValue;
    }

    public static CategoryCode from(String codeValue) {
        return new CategoryCode(codeValue);
    }

    public String getValue() {
        return code;
    }

    public boolean isRootCode() {
        return this.code.length() == 2;
    }

    @Override
    public String toString() {
        return code;
    }

    private String newCodeValue() {
        return StringUtils.randomString(2);
    }
}
