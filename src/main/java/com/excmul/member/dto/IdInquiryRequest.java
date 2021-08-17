package com.excmul.member.dto;

import com.excmul.member.domain.vo.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class IdInquiryRequest {
    private NameVo name;
    private BirthVo birth;
    private PhoneNumberVo phoneNumber;
}
