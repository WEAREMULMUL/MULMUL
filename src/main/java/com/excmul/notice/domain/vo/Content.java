package com.excmul.notice.domain.vo;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
@EqualsAndHashCode
public class Content {
    @Lob
    @Column(name = "NOTICE_CONTENT")
    private String content;

    protected Content() {
    }

    public Content(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return this.value();
    }

    public String value() {
        return content;
    }

}
