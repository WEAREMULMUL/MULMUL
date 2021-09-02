package com.excmul.follow.domain;

import com.excmul.common.domain.AbstractEntity;
import com.excmul.member.domain.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "FOLLOW")
public class Follow extends AbstractEntity<Long> {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FOLLOW_FROM_MEMBER")
    private Member fromMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FOLLOW_TO_MEMBER")
    private Member toMember;

    public Follow(Member fromMember, Member toMember) {
        this.fromMember = fromMember;
        this.toMember = toMember;
    }

}