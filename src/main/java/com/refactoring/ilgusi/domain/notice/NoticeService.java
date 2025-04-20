package com.refactoring.ilgusi.domain.notice;


import com.refactoring.ilgusi.domain.notice.dto.NoticeResponseDto;

public interface NoticeService {
    NoticeResponseDto selectNoticeListApi(int reqPage, String keyword);

    Notice selectNoticeView(int nNo);
    void insertNotice(Notice n);
    void deleteNotice(int nNo);
    void updateNotice(Notice n);
}
