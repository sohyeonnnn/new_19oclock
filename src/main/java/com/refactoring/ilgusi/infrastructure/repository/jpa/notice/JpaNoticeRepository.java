package com.refactoring.ilgusi.infrastructure.repository.jpa.notice;

import com.refactoring.ilgusi.domain.notice.Notice;
import com.refactoring.ilgusi.domain.notice.NoticeRepository;

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
    public Optional<Notice> selectNoticeView(int nNo) {
        return jpaRepository.findById(nNo);
    }

    @Override
    public void insertNotice(Notice n) {
        System.out.println("repository");
        System.out.println(jpaRepository.save(n).toString());
        jpaRepository.save(n);
    }

    @Override
    public void deleteNotice(int nNo) {
        jpaRepository.deleteById(nNo);
    }

    @Override
    public void updateNotice(Notice n) {
        jpaRepository.save(n);
    }

    @Override
    public int totalCount(String keyword) {
        return (int) jpaRepository.countByKeyword(keyword);
    }
}
