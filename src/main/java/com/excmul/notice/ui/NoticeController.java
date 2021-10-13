package com.excmul.notice.ui;

import com.excmul.auth.dto.AuthPrincipal;
import com.excmul.notice.application.NoticeService;
import com.excmul.notice.dto.NoticeRegistryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;


    @PostMapping("/notice")
    @PreAuthorize("hasAnyRole( 'ROLE_ADMIN')")
    public void addNotice(@AuthenticationPrincipal AuthPrincipal authPrincipal, NoticeRegistryDto noticeRegistryDto) {
        noticeService.add(
                authPrincipal.getId(),
                noticeRegistryDto.getTitle(),
                noticeRegistryDto.getContent()
        );
    }

    @PostMapping("/notice/{id}")
    @PreAuthorize("hasAnyRole( 'ROLE_ADMIN')")
    public void deleteNotice(@PathVariable Long id) {
        noticeService.delete(id);
    }
}
