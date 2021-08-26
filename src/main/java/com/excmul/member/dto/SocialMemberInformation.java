package com.excmul.member.dto;

import com.excmul.member.domain.vo.BirthVo;
import com.excmul.member.domain.vo.GenderVo;
import com.excmul.member.domain.vo.NicknameVo;
import com.excmul.member.domain.vo.PhoneNumberVo;
import lombok.Data;
import lombok.NonNull;

@Data
public class SocialMemberInformation {
    @NonNull
    private NicknameVo nickname;

    @NonNull
    private GenderVo gender;

    @NonNull
    private BirthVo birth;

    @NonNull
    private PhoneNumberVo phoneNumber;

    @NonNull
    private boolean termLocation;
}
