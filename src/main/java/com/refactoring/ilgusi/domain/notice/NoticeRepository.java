package com.refactoring.ilgusi.domain.notice;


import java.util.List;
import java.util.Optional;

public interface NoticeRepository {
    List<Notice> selectNoticeList(int offset, int limit, String keyword);
    Optional<Notice> selectNoticeView(int nNo);
    void insertNotice(Notice n);
    void deleteNotice(int nNo);
    void updateNotice(Notice n);
    int totalCount(String keyword);
}
