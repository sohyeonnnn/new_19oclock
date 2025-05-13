package com.refactoring.ilgusi.domain.notice.interfaces;


import com.refactoring.ilgusi.domain.notice.Notice;
import com.refactoring.ilgusi.domain.notice.dto.NoticePageDto;
import com.refactoring.ilgusi.domain.notice.dto.NoticeViewDto;

public interface NoticeService {
    NoticePageDto selectNoticeListApi(int reqPage, String keyword);
    NoticeViewDto selectNoticeView(int noticeNo);
    void insertNotice(Notice notice);
    void deleteNotice(int noticeNo);
    void updateNotice(Notice notice);
}
