package com.excmul.mail.domain;

import com.excmul.common.domain.AbstractEntity;
import com.excmul.mail.domain.vo.Content;
import com.excmul.member.domain.vo.Email;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@ToString
@Entity
@Table(name = "MAIL")
public class Mail extends AbstractEntity<Long> {
    @AttributeOverride(
            name = "email",
            column = @Column(name = "MAIL_RECEIVER")
    )
    private Email receiver;

    @AttributeOverride(
            name = "content",
            column = @Column(name = "MAIL_CONTENT")
    )
    private Content content;

    @Column(name = "MAIL_SENT_DATE")
    private LocalDateTime sentDate;

    protected Mail() {
    }

    public Mail(Email receiver, Content content) {
        this.receiver = receiver;
        this.content = content;
    }

    public void registerSendHistory() {
        sentDate = LocalDateTime.now();
    }
}
