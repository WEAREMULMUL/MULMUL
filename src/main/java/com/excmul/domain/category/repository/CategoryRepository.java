package com.excmul.domain.category.repository;

import com.excmul.domain.category.CategoryCode;
import com.excmul.domain.category.CategoryVO;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryVO, CategoryCode> {
    boolean existsByCode(CategoryCode categoryCode);

    // 상위 카테고리와 하위 카테고리 모두 조인
    @EntityGraph(attributePaths = { "parent", "children" })
    @Query("SELECT c FROM CategoryVO c")
    List<CategoryVO> findAllWithGraph();
}
