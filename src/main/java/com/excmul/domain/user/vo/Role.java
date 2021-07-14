package com.excmul.domain.user.vo;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Role {

    GUEST("손님"),
    USER("사용자"),
    ADMIN("관리자");

    // 작업 진행중
    private final String role;

    Role(final String role) {
        this.role = role;
    }

    public static Role findByRole(String input) {
        return Arrays.stream(Role.values())
                .filter(r -> r.role.equals(input))
                .findAny()
                .orElse(GUEST);
    }
}