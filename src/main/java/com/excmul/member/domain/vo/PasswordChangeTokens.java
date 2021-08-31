package com.excmul.member.domain.vo;

import com.excmul.member.domain.PasswordChangeToken;

import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class PasswordChangeTokens {
    private List<PasswordChangeToken> passwordChangeTokens;

    public PasswordChangeTokens(final List<PasswordChangeToken> passwordChangeTokens) {
        this.passwordChangeTokens = passwordChangeTokens;
    }

    protected PasswordChangeTokens() {
        this(new ArrayList<>());
    }
}
