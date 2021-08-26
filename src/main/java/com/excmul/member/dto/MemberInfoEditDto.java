package com.excmul.member.dto;

import com.excmul.member.domain.vo.Birth;
import com.excmul.member.domain.vo.Nickname;
import com.excmul.member.domain.vo.Password;
import com.excmul.member.domain.vo.PhoneNumber;
import lombok.Data;

@Data
public class MemberInfoEditDto {
    private Password password;
    private Nickname nickname;
    private Birth birth;
    private PhoneNumber phoneNumber;
}
