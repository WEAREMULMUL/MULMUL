package com.excmul.member.dto;

import com.excmul.member.domain.vo.BirthVo;
import com.excmul.member.domain.vo.NameVo;
import com.excmul.member.domain.vo.PhoneNumberVo;
import lombok.Data;

@Data
public class MemberPrivacyDto {
    private NameVo name;
    private BirthVo birth;
    private PhoneNumberVo phoneNumber;
}
