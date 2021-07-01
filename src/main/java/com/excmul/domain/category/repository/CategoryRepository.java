package com.excmul.domain.category.repository;

import com.excmul.domain.category.CategoryCode;
import com.excmul.domain.category.CategoryVO;
import com.excmul.domain.category.dto.CategoryNode;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryVO, CategoryCode> {
    boolean existsByCode(CategoryCode categoryCode);

    // 지정한 레벨까지의 카테고리를 불러온다.
    @Query("SELECT c FROM CategoryVO c WHERE length(c.code.code) <= :maxLevel * 2")
    List<CategoryVO> findAllByLevel(int maxLevel);

    // 지정한 레벨까지의 카테고리를 상위, 하위 카테고리와 함꼐 불러온다.
    @EntityGraph(attributePaths = { "parent", "children" })
    @Query("SELECT c FROM CategoryVO c WHERE length(c.code.code) <= :maxLevel * 2")
    List<CategoryVO> findByLevelWithGraph(int maxLevel);

    @EntityGraph(attributePaths = { "parent", "children" })
    @Query("SELECT c FROM CategoryVO c WHERE c.code = :categoryCode")
    CategoryVO findByCodeWithGraph(CategoryCode categoryCode);
}
