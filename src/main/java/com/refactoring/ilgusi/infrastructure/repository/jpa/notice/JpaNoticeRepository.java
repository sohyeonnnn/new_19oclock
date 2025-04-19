package com.refactoring.ilgusi.infrastructure.repository.jpa.notice;

import com.refactoring.ilgusi.domain.notice.Notice;
import com.refactoring.ilgusi.domain.notice.NoticeRepository;

import java.util.ArrayList;
import java.util.Optional;

public class JpaNoticeRepository implements NoticeRepository {
    private final SpringDataNoticeRepository jpaRepository;

    public JpaNoticeRepository(SpringDataNoticeRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }


    @Override
    public ArrayList<Notice> selectNoticeList(int start, int end, String keyword) {
        return null;
    }

    @Override
    public Optional<Notice> selectNoticeView(int nNo) {
        return Optional.empty();
    }

    @Override
    public void insertNotice(Notice n) {

    }

    @Override
    public void deleteNotice(int nNo) {

    }

    @Override
    public void updateNotice(Notice n) {

    }

    @Override
    public int totalCount() {
        return 0;
    }
}
