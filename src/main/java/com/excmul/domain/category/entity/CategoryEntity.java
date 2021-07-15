package com.excmul.domain.category.entity;

import com.excmul.domain.category.dto.CategoryNodeSupporter;
import com.excmul.domain.category.vo.CategoryCode;
import com.excmul.domain.common.date.DateEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Access(AccessType.FIELD)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CategoryEntity extends DateEntity implements CategoryNodeSupporter {
    @EmbeddedId
    private CategoryCode code;

    @Column(length = 20, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private CategoryEntity parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CategoryEntity> children;

    @Builder
    public CategoryEntity(CategoryEntity parent, String name) {
        this.parent = parent;
        this.code = newCode();
        this.name = name;
    }

    private CategoryCode newCode() {
        return parent == null ? CategoryCode.newRootInstance() : CategoryCode.newChildInstance(parent.getCode());
    }

    public void changeCode() {
        if (!isLeafCategory())
            throw new RuntimeException("하위 카테고리가 존재해 카테고리 코드를 변경할 수 없습니다."); // TODO :: Exception 클래스 생성 후 교체하기
        this.code = newCode();
    }
}
