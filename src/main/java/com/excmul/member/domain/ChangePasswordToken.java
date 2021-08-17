package com.excmul.member.domain;

import com.excmul.common.domain.TimeTokenEntity;
import com.excmul.common.domain.vo.TokenVo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ChangePasswordToken extends TimeTokenEntity {
    @Transient private static final int TOKEN_LENGTH = 25;
    @Transient private static final int EXPIRY_HOURS = 24;

    @ManyToOne
    @JoinColumn(name = "TOKEN_MEMBER")
    private Member member;

    public ChangePasswordToken(TokenVo token, LocalDateTime expiryTime, Member member) {
        super(token, expiryTime);
        this.member = member;
    }

    public static ChangePasswordToken newInstance(Member member) {
        TokenVo token = TokenVo.newInstance(TOKEN_LENGTH);
        LocalDateTime expiryTime = LocalDateTime.now().plusHours(EXPIRY_HOURS);

        return new ChangePasswordToken(token, expiryTime, member);
    }
}
