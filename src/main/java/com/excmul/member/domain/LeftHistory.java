package com.excmul.member.domain;

import com.excmul.common.domain.AbstractEntity;
import com.excmul.member.dto.LeftHistoryDto;

import javax.persistence.*;

@Entity
public class LeftHistory extends AbstractEntity<Long> {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LEFT_HISTORY_MEMBER")
    private Member member;

    @Column(name = "LEFT_HISTORY_LEFT", nullable = false)
    private boolean left;

    protected LeftHistory() {

    }

    public LeftHistory(final Member member, final boolean left) {
        this.member = member;
        this.left = left;
    }

    public LeftHistoryDto toDto() {
        return LeftHistoryDto.builder()
                .left(left)
                .date(createdDate)
                .build();
    }
}
