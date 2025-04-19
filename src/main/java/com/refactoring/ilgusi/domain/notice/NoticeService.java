package com.refactoring.ilgusi.domain.notice;


import com.refactoring.ilgusi.domain.notice.dto.NoticePageDto;

public interface NoticeService {
    NoticePageDto selectNoticeList(int reqPage, String keyword);
    Notice selectNoticeView(int nNo);
    void insertNotice(Notice n);
    void deleteNotice(int nNo);
    void updateNotice(Notice n);
}
