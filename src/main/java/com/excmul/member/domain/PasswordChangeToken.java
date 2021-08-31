package com.excmul.member.domain;

import com.excmul.common.domain.TimeTokenEntity;
import com.excmul.common.domain.Url;
import com.excmul.common.domain.vo.TokenSerial;
import com.excmul.mail.domain.Mail;
import com.excmul.mail.domain.vo.Content;
import com.excmul.member.domain.vo.Password;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(
        indexes = {
                @Index(name = "INDEX_TOKEN", columnList = "TOKEN_TOKEN_SERIAL", unique = true)
        }
)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class PasswordChangeToken extends TimeTokenEntity {
    @Transient private static final int TOKEN_LENGTH = 25;
    @Transient private static final int EXPIRY_HOURS = 24;

    @Transient private static final String BASE_MAIL_CONTENT = "패스워드 변경 주소는 %s 입니다.";
    @Transient private static final Url BASE_PASSWORD_CHANGE_URL =
            Url.siteUrl()
                    .append("auth")
                    .append("changePassword");

    @ManyToOne
    @JoinColumn(name = "TOKEN_MEMBER", updatable = false)
    private Member member;

    @AttributeOverride(
            name = "password",
            column = @Column(name = "TOKEN_OLD_PASSWORD", updatable = false)
    )
    private Password oldPassword;

    @AttributeOverride(
            name = "password",
            column = @Column(name = "TOKEN_CHANGED_PASSWORD")
    )
    private Password changedPassword;

    private PasswordChangeToken(TokenSerial token, LocalDateTime expiryTime, Member member, Password oldPassword) {
        super(token, expiryTime);
        this.member = member;
        this.oldPassword = oldPassword;
    }

    public static PasswordChangeToken newInstance(Member member, Password oldPassword) {
        TokenSerial token = TokenSerial.newRandomInstance(TOKEN_LENGTH);
        LocalDateTime expiryTime = LocalDateTime.now().plusHours(EXPIRY_HOURS);

        return new PasswordChangeToken(token, expiryTime, member, oldPassword);
    }

    public void use(Password changedPassword) {
        validateAvailable();

        member.changePassword(changedPassword);
        this.changedPassword = changedPassword;
        this.used = true;
    }

    public Mail newMail() {
        return member.newMail(newMailContent());
    }

    private Content newMailContent() {
        Url passwordChangeUrl = BASE_PASSWORD_CHANGE_URL.append(
                tokenSerial.value()
        );
        String contentValue = String.format(BASE_MAIL_CONTENT, passwordChangeUrl.value());
        return new Content(contentValue);
    }
}
