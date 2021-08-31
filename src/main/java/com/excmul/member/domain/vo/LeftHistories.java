package com.excmul.member.domain.vo;

import com.excmul.member.domain.LeftHistory;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@Embeddable
public class LeftHistories {
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LeftHistory> leftHistories;

    public LeftHistories(final List<LeftHistory> leftHistories) {
        this.leftHistories = leftHistories;
    }

    protected LeftHistories() {
        this(new ArrayList<>());
    }

    public void add(LeftHistory leftHistory) {
        this.leftHistories.add(
                leftHistory
        );
    }
}
