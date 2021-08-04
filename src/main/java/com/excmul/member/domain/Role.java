package com.excmul.member.domain;

import javax.persistence.Column;

public enum Role {

    GUEST("손님"),
    USER("사용자"),
    ADMIN("관리자");

    @Column(nullable = false)
    private final String value;

    Role(final String role) {
        this.value = role;
    }

    public String getValue() {
        return value;
    }
}