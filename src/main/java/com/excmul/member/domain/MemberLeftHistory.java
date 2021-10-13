package com.excmul.member.domain;

import com.excmul.common.domain.AbstractEntity;
import com.excmul.member.dto.LeftHistoryDto;

import javax.persistence.*;

@Table(name = "MEMBER_LEFT_HISTORY")
@Entity
public class MemberLeftHistory extends AbstractEntity<Long> {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MEMBER_LEFT_HISTORY_MEMBER")
    private Member member;

    @Column(name = "MEMBER_LEFT_HISTORY_LEFT", nullable = false)
    private boolean left;

    protected MemberLeftHistory() {
    }

    public MemberLeftHistory(final Member member, final boolean left) {
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
