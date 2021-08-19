package com.excmul.common.domain;

import com.excmul.common.domain.vo.TokenVo;
import com.excmul.common.exception.TokenException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.excmul.common.exception.TokenException.ErrorCode;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
public abstract class TimeTokenEntity extends AbstractEntity {
    @Embedded
    protected TokenVo token;

    @Column(name = "TOKEN_USED", nullable = false)
    protected boolean used;

    @Column(name = "EXPIRY_DATE", nullable = false)
    protected LocalDateTime expiryDate;

    public TimeTokenEntity(TokenVo token, LocalDateTime expiryDate) {
        this.token = token;
        this.expiryDate = expiryDate;

        this.used = false;
    }

    public boolean isAvailable() {
        return !isUsed() && !isTimeout();
    }

    protected void validateAvailable() {
        if (isUsed()) {
            throw new TokenException(ErrorCode.ALREADY_USED);
        }
        if (isTimeout()) {
            throw new TokenException(ErrorCode.TIME_OUT);
        }
    }

    protected boolean isUsed() {
        return used;
    }

    protected boolean isTimeout() {
        return expiryDate.isBefore(LocalDateTime.now());
    }
}