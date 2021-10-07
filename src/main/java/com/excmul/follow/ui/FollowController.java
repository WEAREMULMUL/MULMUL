package com.excmul.follow.ui;

import com.excmul.auth.dto.AuthPrincipal;
import com.excmul.follow.dto.FollowCountDto;
import com.excmul.follow.exception.FollowException;
import com.excmul.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class FollowController {
    private final MemberService memberService;

    @GetMapping("/follow/member")
    public String followingMember() {
        return "fragments/contents/follow/follow-following-member";
    }

    @PostMapping("/follow/member")
    public String followMemberResult(@AuthenticationPrincipal AuthPrincipal principal, Long id) {
        if (principal.getId() == id) {
            throw new FollowException(FollowException.ErrorCode.IS_SAME_MEMBER);
        }
        memberService.follow(principal.getId(), id);
        return "redirect:/follow/result";
    }

    @GetMapping("/follow/result")
    public String followResult(@AuthenticationPrincipal AuthPrincipal principal, Model model) {
        int to = memberService.countFollowTo(principal.getId());
        int from = memberService.countFollowFrom(principal.getId());
        FollowCountDto follow = new FollowCountDto(to, from);
        model.addAttribute("follow", follow);
        return "fragments/contents/follow/follow-result";
    }
}
