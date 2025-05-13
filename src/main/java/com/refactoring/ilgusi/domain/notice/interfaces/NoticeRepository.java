package com.refactoring.ilgusi.domain.notice.interfaces;


import com.refactoring.ilgusi.domain.notice.Notice;

import java.util.List;
import java.util.Optional;

public interface NoticeRepository {
    List<Notice> selectNoticeList(int offset, int limit, String keyword);
    Optional<Notice> selectNoticeView(int noticeNo);
    void insertNotice(Notice notice);
    void deleteNotice(int noticeNo);
    void updateNotice(Notice notice);
    int totalCount(String keyword);
}
