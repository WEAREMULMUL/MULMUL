package com.excmul.domain.category;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
public class CategoryVO {
    @EmbeddedId
    private final CategoryCode code;

    @ManyToOne(fetch = FetchType.LAZY)
    private CategoryVO parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<CategoryVO> children;

    @Builder
    public CategoryVO(CategoryVO parent) {
        this.code = new CategoryCode(parent == null ? null : parent.getCode());
    }

    public CategoryVO() {
        this(null);
    }
}
