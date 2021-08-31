package com.excmul.member.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LeftHistoryDto {
    private boolean left;

    private LocalDateTime date;

    @Builder
    public LeftHistoryDto(final boolean left, final LocalDateTime date) {
        this.left = left;
        this.date = date;
    }
}
