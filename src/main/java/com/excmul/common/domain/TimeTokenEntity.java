package com.excmul.common.domain;

import com.excmul.common.domain.vo.TokenVo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
public abstract class TimeTokenEntity extends AbstractEntity {
    @Embedded
    private TokenVo token;

    @Column(name = "TOKEN_USED", nullable = false)
    private boolean used;

    @Column(name = "EXPIRY_DATE", nullable = false)
    private LocalDateTime expiryDate;

    public TimeTokenEntity(TokenVo token, LocalDateTime expiryDate) {
        this.token = token;
        this.expiryDate = expiryDate;

        this.used = false;
    }

    public boolean isUsed() {
        return used && expiryDate.isBefore(LocalDateTime.now());
    }

    public void use() {
        this.used = true;
    }
}
