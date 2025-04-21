package com.refactoring.ilgusi.domain.notice;


import com.refactoring.ilgusi.domain.notice.dto.NoticePageResponseDto;
import com.refactoring.ilgusi.domain.notice.dto.NoticeViewDto;

public interface NoticeService {
    NoticePageResponseDto selectNoticeListApi(int reqPage, String keyword);
    NoticeViewDto selectNoticeView(int nNo);
    void insertNotice(Notice n);
    void deleteNotice(int nNo);
    void updateNotice(Notice n);
}
