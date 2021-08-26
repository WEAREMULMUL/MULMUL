package com.excmul.common.domain;

import com.excmul.common.domain.vo.Token;
import com.excmul.common.exception.TokenException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

import static com.excmul.common.exception.TokenException.ErrorCode;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
public abstract class TimeTokenEntity extends AbstractEntity<Long> {
    @Embedded
    protected Token token;

    @Column(name = "TOKEN_USED", nullable = false)
    protected boolean used;

    @Column(name = "EXPIRY_DATE", nullable = false)
    protected LocalDateTime expiryDate;

    public TimeTokenEntity(Token token, LocalDateTime expiryDate) {
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
