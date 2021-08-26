package com.excmul.auth.dto;

import com.excmul.member.domain.vo.EmailVo;
import com.excmul.member.domain.vo.NameVo;
import com.excmul.member.domain.vo.SocialType;

public interface SocialAttributes {
    SocialType socialType();

    String userKey();

    NameVo name();

    EmailVo email();
}
