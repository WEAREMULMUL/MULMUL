package com.excmul.member.dto;

import com.excmul.member.domain.vo.Birth;
import com.excmul.member.domain.vo.Name;
import com.excmul.member.domain.vo.PhoneNumber;
import lombok.Data;

@Data
public class MemberPrivacyDto {
    private Name name;
    private Birth birth;
    private PhoneNumber phoneNumber;
}
