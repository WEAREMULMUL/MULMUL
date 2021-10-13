package com.excmul.notice.application;

import com.excmul.member.domain.Member;
import com.excmul.member.domain.MemberRepository;
import com.excmul.notice.exception.NotFoundMemberException;
import com.excmul.notice.domain.Notice;
import com.excmul.notice.domain.NoticeRepository;
import com.excmul.notice.domain.vo.Content;
import com.excmul.notice.domain.vo.Title;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final MemberRepository memberRepository;
    private final NoticeRepository noticeRepository;

    @Transactional
    public void add(Long id, Title title, Content content) {
        Member member = memberRepository.findById(id)
                .orElseThrow(NotFoundMemberException::new);
        Notice notice = Notice.of(title, content, member);
        member.addNotice(notice);
    }

    @Transactional
    public void delete(Long id) {
        noticeRepository.deleteById(id);
    }

    @Transactional
    public void edit(Long id, Title title, Content content) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(NotFoundMemberException::new);
        notice.update(title, content);
    }

}
