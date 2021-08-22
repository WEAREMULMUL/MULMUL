package com.excmul.member.dto;

import com.excmul.member.domain.Member;
import com.excmul.member.domain.vo.*;
import lombok.*;

@Data
@NoArgsConstructor
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
