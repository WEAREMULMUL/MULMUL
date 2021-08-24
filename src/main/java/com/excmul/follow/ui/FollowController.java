package com.excmul.follow.ui;

import com.excmul.auth.oauth.AuthPrincipal;
import com.excmul.follow.application.FollowService;
import com.excmul.follow.dto.FollowDto;
import com.excmul.member.domain.vo.EmailVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @GetMapping("/follow/member")
    public String followingMember() {
        return "fragments/contents/follow/follow-following-member";
    }

    @PostMapping("/follow/member")
    public String followMemberResult(@AuthenticationPrincipal AuthPrincipal principal, EmailVo email) {
        EmailVo fromMail = principal.loginMember().email();
        followService.followMember(fromMail, email);
        return "redirect:/follow/result";
    }

    @GetMapping("/unfollow/member")
    public String unfollowingMember() {
        return "fragments/contents/follow/follow-unfollowing-member";
    }

    @PostMapping("/unfollow/member")
    public String unfollowMemberResult(@AuthenticationPrincipal AuthPrincipal principal, EmailVo email) {
        EmailVo fromMail = principal.loginMember().email();
        followService.unfollowMember(fromMail, email);
        return "redirect:/follow/result";
    }

    @GetMapping("/follow/result")
    public String followResult(@AuthenticationPrincipal AuthPrincipal principal, Model model) {
        EmailVo email = principal.loginMember().email();
        int countFollowFromMe = followService.countFollowFromMe(email);
        int countFollowToMe = followService.countFollowToMe(email);
        FollowDto follow = new FollowDto(countFollowFromMe, countFollowToMe);

        model.addAttribute("follow", follow);
        
        return "fragments/contents/follow/follow-result";
    }
}
