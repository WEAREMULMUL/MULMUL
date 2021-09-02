package com.excmul.follow.ui;

import com.excmul.auth.dto.AuthPrincipal;
import com.excmul.follow.dto.FollowDto;
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
    public String followMemberResult(@AuthenticationPrincipal AuthPrincipal principal, long id) {
        if (principal.getId() == id) {
            throw new FollowException(FollowException.ErrorCode.IS_SAME_MEMBER);
        }
        memberService.follow(principal.getId(), id);
        return "redirect:/follow/result";
    }

    @GetMapping("/unfollow/member")
    public String unfollowingMember() {
        return "fragments/contents/follow/follow-unfollowing-member";
    }

    @PostMapping("/unfollow/member")
    public String unfollowMemberResult(@AuthenticationPrincipal AuthPrincipal principal, long id) {
        if (principal.getId() == id) {
            throw new FollowException(FollowException.ErrorCode.IS_SAME_MEMBER);
        }
        memberService.unfollow(principal.getId(), id);
        return "redirect:/follow/result";
    }

    @GetMapping("/follow/result")
    public String followResult(@AuthenticationPrincipal AuthPrincipal principal, Model model) {
        int countFollowFromMe = memberService.countFromFollow(principal.getId());
        int countFollowToMe = memberService.countToFollow(principal.getId());
        FollowDto follow = new FollowDto(countFollowFromMe, countFollowToMe);

        model.addAttribute("follow", follow);

        return "fragments/contents/follow/follow-result";
    }
}
