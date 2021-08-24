package com.excmul.follow.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FollowDto {
    private int countFollowFromMe;
    private int countFollowToMe;

    public FollowDto(int countFollowFromMe, int countFollowToMe) {
        this.countFollowFromMe = countFollowFromMe;
        this.countFollowToMe = countFollowToMe;
    }
}
