package com.excmul.member.dto;

import com.excmul.member.domain.vo.Password;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberChangePasswordDto {
    private Password beforeChangePassword;
    private Password afterChangePassword;
}