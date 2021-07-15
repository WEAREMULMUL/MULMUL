package com.excmul.domain.category.vo;

import com.excmul.util.random.impl.ThreadRandomUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Access(AccessType.FIELD)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class CategoryCode implements Serializable {
    private static final char[] CODE_PATTERNS = "1234567890QWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();

    private String code;

    public final static CategoryCode newInstance(String codeValue) {
        return new CategoryCode(codeValue);
    }

    public final static CategoryCode newRootInstance() {
        return newInstance(newCodeValue());
    }

    public final static CategoryCode newChildInstance(CategoryCode parentCode) {
        if (parentCode == null)
            return newRootInstance();
        return newInstance(parentCode + newCodeValue());
    }

    @JsonIgnore
    public boolean isRootCode() {
        return this.code.length() == 2;
    }

    public String getValue() {
        return code;
    }

    @Override
    public String toString() {
        return code;
    }

    private static String newCodeValue() {
        return ThreadRandomUtils.randomString(2, CODE_PATTERNS);
    }
}
