package com.excmul.member.dto;

import com.excmul.member.domain.vo.Birth;
import com.excmul.member.domain.vo.Gender;
import com.excmul.member.domain.vo.Nickname;
import com.excmul.member.domain.vo.PhoneNumber;
import lombok.Data;
import lombok.NonNull;

@Data
public class SocialMemberInformationDto {
    @NonNull
    private Nickname nickname;

    @NonNull
    private Gender gender;

    @NonNull
    private Birth birth;

    @NonNull
    private PhoneNumber phoneNumber;

    @NonNull
    private boolean termLocation;
}
