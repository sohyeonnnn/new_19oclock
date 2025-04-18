package com.refactoring.ilgusi.infrastructure.repository.jpa.notice;

import com.refactoring.ilgusi.domain.notice.NoticeRepository;



public class JpaNoticeRepository implements NoticeRepository {
    private final SpringDataNoticeRepository jpaRepository;

    public JpaNoticeRepository(SpringDataNoticeRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }


}
