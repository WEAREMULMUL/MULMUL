package com.excmul.auth.dto;

import com.excmul.member.domain.vo.Email;
import com.excmul.member.domain.vo.Name;
import com.excmul.member.domain.vo.SocialType;

public interface SocialAttributes {
    SocialType socialType();

    String userKey();

    Name name();

    Email email();
}
