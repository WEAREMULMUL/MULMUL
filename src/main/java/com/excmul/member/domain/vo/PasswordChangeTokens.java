package com.excmul.member.domain.vo;

import com.excmul.common.domain.vo.TokenSerial;
import com.excmul.member.domain.PasswordChangeToken;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Embeddable
public class PasswordChangeTokens {
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PasswordChangeToken> passwordChangeTokens;

    public PasswordChangeTokens(final List<PasswordChangeToken> passwordChangeTokens) {
        this.passwordChangeTokens = passwordChangeTokens;
    }

    protected PasswordChangeTokens() {
        this(new ArrayList<>());
    }

    public void add(PasswordChangeToken token) {
        passwordChangeTokens.add(token);
    }
}
