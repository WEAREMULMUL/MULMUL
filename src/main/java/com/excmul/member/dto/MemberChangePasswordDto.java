package com.excmul.member.dto;

import com.excmul.member.domain.vo.Password;
import lombok.Data;

@Data
public class MemberChangePasswordDto {
    private Password beforeChangePassword;
    private Password afterChangePassword;
}