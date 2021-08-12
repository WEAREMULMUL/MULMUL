package com.excmul.auth.OAuth2;

import com.excmul.member.domain.vo.NicknameVo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
public class SessionUser implements Serializable {
    private final NicknameVo nickname;
    private final String email;
}
