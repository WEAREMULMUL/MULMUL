package com.excmul.mail.domain.vo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Lob;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Embeddable
public class Content {
    @Lob
    @Column(name = "CONTENT")
    private String content;

    public Content(String content) {
        this.content = content;
    }
}
