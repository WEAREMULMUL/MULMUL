package com.excmul.follow.dto;

import lombok.Data;

@Data
public class FollowCountDto {
    private int countFollowToMe;
    private int countFollowFromMe;

    public FollowCountDto(int countFollowToMe, int countFollowFromMe) {
        this.countFollowToMe = countFollowToMe;
        this.countFollowFromMe = countFollowFromMe;
    }
}
