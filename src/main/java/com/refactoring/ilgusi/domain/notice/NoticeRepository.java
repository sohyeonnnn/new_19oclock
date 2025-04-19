package com.refactoring.ilgusi.domain.notice;

import java.util.ArrayList;
import java.util.Optional;

public interface NoticeRepository {
    ArrayList<Notice> selectNoticeList(int start, int end, String keyword);
    Optional<Notice> selectNoticeView(int nNo);
    void insertNotice(Notice n);
    void deleteNotice(int nNo);
    void updateNotice(Notice n);
    int totalCount();

}
