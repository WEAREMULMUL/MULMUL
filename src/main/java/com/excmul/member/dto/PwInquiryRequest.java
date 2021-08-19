package com.excmul.member.dto;

import com.excmul.member.domain.vo.BirthVo;
import com.excmul.member.domain.vo.EmailVo;
import com.excmul.member.domain.vo.NameVo;
import com.excmul.member.domain.vo.PhoneNumberVo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PwInquiryRequest {
    private EmailVo email;
    private NameVo name;
    private BirthVo birth;
    private PhoneNumberVo phoneNumber;
}
