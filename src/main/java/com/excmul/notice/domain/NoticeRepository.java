package com.excmul.notice.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    @Override
    void deleteById(Long id);

    @Override
    Optional<Notice> findById(Long id);
}
