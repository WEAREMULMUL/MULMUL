package com.excmul.notice.domain;

import com.excmul.common.domain.AbstractEntity;
import com.excmul.member.domain.Member;
import com.excmul.notice.domain.vo.Content;
import com.excmul.notice.domain.vo.Title;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Entity
@Table(name = "NOTICE")
public class Notice extends AbstractEntity<Long> {
    @Embedded
    private Title title;

    @Embedded
    private Content content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NOTICE_MEMBER")
    private Member noticeMember;

    protected Notice() {

    }

    public static Notice of(Title title, Content content, Member member) {
        return Notice.builder()
                .title(title)
                .content(content)
                .noticeMember(member)
                .build();
    }

    public void update(Title title, Content content) {
        this.title = title;
        this.content = content;
    }
}
