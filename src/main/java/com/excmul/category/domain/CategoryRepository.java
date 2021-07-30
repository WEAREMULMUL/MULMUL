package com.excmul.category.domain;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, CategoryCode> {
    boolean existsByCode(CategoryCode categoryCode);

    // 지정한 레벨까지의 카테고리를 불러온다.
    @Query("SELECT c FROM CategoryEntity c WHERE length(c.code.code) <= :maxLevel * 2")
    List<CategoryEntity> findAllByLevel(int maxLevel);

    // 지정한 레벨까지의 카테고리를 상위, 하위 카테고리와 함꼐 불러온다.
    @EntityGraph(attributePaths = { "parent", "children" })
    @Query("SELECT c FROM CategoryEntity c WHERE length(c.code.code) <= :maxLevel * 2")
    List<CategoryEntity> findByLevelWithGraph(int maxLevel);

    @EntityGraph(attributePaths = { "parent", "children" })
    @Query("SELECT c FROM CategoryEntity c WHERE c.code = :categoryCode")
    CategoryEntity findByCodeWithGraph(CategoryCode categoryCode);
}
