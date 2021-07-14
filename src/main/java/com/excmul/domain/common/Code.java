package com.excmul.domain.common;

import com.excmul.util.random.impl.UUIDRandomUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public abstract class Code implements Serializable {

    @Column(nullable = false)
    private String value;

    public Code() {
        createCode();
    }

    private void createCode() {
        this.value = createRandom();
    }

    private String createRandom() {
        return new UUIDRandomUtils().getValue();
    }
}
