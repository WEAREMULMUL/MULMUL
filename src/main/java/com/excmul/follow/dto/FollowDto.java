package com.excmul.follow.dto;

import lombok.Data;

@Data
public class FollowDto {
    private int countFollowToMe;
    private int countFollowFromMe;

    public FollowDto(int countFollowToMe, int countFollowFromMe) {
        this.countFollowToMe = countFollowToMe;
        this.countFollowFromMe = countFollowFromMe;
    }
}
