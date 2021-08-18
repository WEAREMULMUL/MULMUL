package com.excmul.mail.domain;

import com.excmul.common.domain.AbstractEntity;
import com.excmul.mail.domain.vo.Content;
import com.excmul.member.domain.vo.EmailVo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "MAIL")
public class Mail extends AbstractEntity {
    @AttributeOverride(
            name = "email",
            column = @Column(name = "MAIL_RECEIVER")
    )
    private EmailVo receiver;

    @AttributeOverride(
            name = "content",
            column = @Column(name = "MAIL_CONTENT")
    )
    private Content content;

    @Column(name = "MAIL_SENT_DATE")
    private LocalDateTime sentDate;

    public Mail(EmailVo receiver, Content content) {
        this.receiver = receiver;
        this.content = content;
    }

    public void send() {
        sentDate = LocalDateTime.now();
    }
}
