package com.excmul.mail.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

@ToString
@EqualsAndHashCode
@Embeddable
public class Content {
    @Lob
    @Column(name = "CONTENT")
    private String content;

    protected Content() {

    }

    public Content(String content) {
        this.content = content;
    }
}
