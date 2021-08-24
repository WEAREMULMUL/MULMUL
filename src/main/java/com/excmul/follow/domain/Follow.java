package com.excmul.follow.domain;

import com.excmul.common.domain.AbstractEntity;
import com.excmul.member.domain.Member;
import lombok.*;

import javax.persistence.*;


@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "FOLLOW")
public class Follow extends AbstractEntity {
    
    @ManyToOne
    @JoinColumn(name = "FOLLOW_FROM_MEMBER")
    private Member fromMember;

    @ManyToOne
    @JoinColumn(name = "FOLLOW_TO_MEMBER")
    private Member toMember;

}