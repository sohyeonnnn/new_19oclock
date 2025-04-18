package com.refactoring.ilgusi.infrastructure.repository.jpa.notice;

import com.refactoring.ilgusi.domain.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataNoticeRepository extends JpaRepository<Notice, Integer> {
}
