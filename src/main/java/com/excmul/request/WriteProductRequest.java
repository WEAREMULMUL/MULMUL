package com.excmul.request;

import com.excmul.domain.category.dto.CategoryNode;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class WriteProductRequest {
    @NotNull(message = "카테고리를 찾을 수 없습니다.")
    private CategoryNode category;
}
