package com.excmul.follow.domain;

import com.excmul.common.domain.AbstractEntity;
import com.excmul.member.domain.Member;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "FOLLOW")
@EqualsAndHashCode
public class Follow extends AbstractEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FOLLOW_TO_MEMBER")
    private Member subjectMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FOLLOW_FROM_MEMBER")
    private Member targetMember;

    protected Follow() {
    }

    public Follow(Member subjectMember, Member targetMember) {
        this.subjectMember = subjectMember;
        this.targetMember = targetMember;
    }
}