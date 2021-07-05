package com.excmul.request;

import com.excmul.domain.category.CategoryCode;
import com.excmul.domain.category.CategoryVO;
import com.excmul.domain.category.dto.CategoryNode;
import lombok.*;
import org.springframework.data.domain.PageRequest;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.awt.print.Pageable;

@Data
public class ProductPageRequest {
    @NotNull(message = "페이지를 찾을 수 없습니다.")
    private CategoryNode category;
}
