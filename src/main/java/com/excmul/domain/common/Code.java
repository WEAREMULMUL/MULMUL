package com.excmul.domain.common;

import com.excmul.util.random.impl.UUIDRandomUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/*
 * :: Code [class]
 *
 * @EmbeddedId
 *
 * 추후 개발 진행하기
 *
 */

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

    // 하나의 인스턴스만 되도록~
    private String createRandom() {
        return new UUIDRandomUtils().getValue();
    }
}
