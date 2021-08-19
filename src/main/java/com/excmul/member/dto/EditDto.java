package com.excmul.member.dto;

import com.excmul.member.domain.vo.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class EditDto {
    private PasswordVo password;
    private NicknameVo nickname;
    private BirthVo birth;
    private PhoneNumberVo phoneNumber;
}
