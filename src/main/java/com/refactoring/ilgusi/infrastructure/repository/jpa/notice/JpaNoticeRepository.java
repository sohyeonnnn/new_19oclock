package com.refactoring.ilgusi.infrastructure.repository.jpa.notice;

import com.refactoring.ilgusi.domain.notice.Notice;
import com.refactoring.ilgusi.domain.notice.interfaces.NoticeRepository;

import java.util.List;
import java.util.Optional;

public class JpaNoticeRepository implements NoticeRepository {
    private final SpringDataNoticeRepository jpaRepository;

    public JpaNoticeRepository(SpringDataNoticeRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Notice> selectNoticeList(int offset, int limit, String keyword) {
        return jpaRepository.findByKeywordWithRange(offset, limit, keyword);
    }

    @Override
    public Optional<Notice> selectNoticeView(int noticeNo) {
        return jpaRepository.findByNoticeNo(noticeNo);
    }

    @Override
    public void insertNotice(Notice notice) {
        jpaRepository.save(notice);
    }

    @Override
    public void deleteNotice(int noticeNo) {
        jpaRepository.deleteByNoticeNo(noticeNo);
    }

    @Override
    public void updateNotice(Notice notice) {
        jpaRepository.save(notice);
    }

    @Override
    public int totalCount(String keyword) {
        return jpaRepository.countByKeyword(keyword);
    }
}
