package com.excmul.follow.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class FollowCountDto {
    @NonNull
    private int countFollowToMe;

    @NonNull
    private int countFollowFromMe;

    public FollowCountDto(int countFollowToMe, int countFollowFromMe) {
        this.countFollowToMe = countFollowToMe;
        this.countFollowFromMe = countFollowFromMe;
    }
}
