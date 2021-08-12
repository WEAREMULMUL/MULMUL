package com.excmul.member.domain.vo;

public enum RoleVo {

    GUEST("손님"),
    USER("사용자"),
    ADMIN("관리자");

    private final String role;

    RoleVo(final String role) {
        this.role = role;
    }

    public String value() {
        return role;
    }
}