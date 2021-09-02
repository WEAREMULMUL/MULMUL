package com.excmul.member.domain.vo;

import com.excmul.member.domain.MemberLeftHistory;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@Embeddable
public class MemberLeftHistories {
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MemberLeftHistory> leftHistories;

    public MemberLeftHistories(final List<MemberLeftHistory> leftHistories) {
        this.leftHistories = leftHistories;
    }

    protected MemberLeftHistories() {
        this(new ArrayList<>());
    }

    public void add(MemberLeftHistory leftHistory) {
        this.leftHistories.add(
                leftHistory
        );
    }
}
