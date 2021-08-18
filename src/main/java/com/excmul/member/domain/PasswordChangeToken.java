package com.excmul.member.domain;

import com.excmul.common.domain.TimeTokenEntity;
import com.excmul.common.domain.Url;
import com.excmul.common.domain.vo.TokenVo;
import com.excmul.mail.domain.Mail;
import com.excmul.mail.domain.vo.Content;
import com.excmul.member.domain.vo.PasswordVo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "PASSWORD_CHANGE_TOKEN")
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
    private PasswordVo oldPassword;

    @AttributeOverride(
            name = "password",
            column = @Column(name = "TOKEN_CHANGED_PASSWORD")
    )
    private PasswordVo changedPassword;

    private PasswordChangeToken(TokenVo token, LocalDateTime expiryTime, Member member, PasswordVo oldPassword) {
        super(token, expiryTime);
        this.member = member;
        this.oldPassword = oldPassword;
    }

    public static PasswordChangeToken newInstance(Member member, PasswordVo oldPassword) {
        TokenVo token = TokenVo.newRandomInstance(TOKEN_LENGTH);
        LocalDateTime expiryTime = LocalDateTime.now().plusHours(EXPIRY_HOURS);

        return new PasswordChangeToken(token, expiryTime, member, oldPassword);
    }

    public void use(PasswordVo changedPassword) {
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
                token.value()
        );
        String contentValue = String.format(BASE_MAIL_CONTENT, passwordChangeUrl);
        return new Content(contentValue);
    }
}
