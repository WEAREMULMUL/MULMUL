package com.excmul.request;

import com.excmul.domain.category.dto.CategoryNode;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
public class ProductPageRequest {
    @NotNull(message = "페이지를 찾을 수 없습니다.")
    private CategoryNode category;
}
