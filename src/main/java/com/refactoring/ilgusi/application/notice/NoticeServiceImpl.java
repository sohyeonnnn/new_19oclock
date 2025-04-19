package com.refactoring.ilgusi.application.notice;

import com.refactoring.ilgusi.domain.notice.Notice;
import com.refactoring.ilgusi.domain.notice.NoticeRepository;
import com.refactoring.ilgusi.domain.notice.NoticeService;
import com.refactoring.ilgusi.domain.notice.dto.NoticePageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;

    @Override
    public NoticePageDto selectNoticeList(int reqPage, String keyword) {
        return null;
        //return noticeRepository.selectNoticeList(reqPage, keyword);
    }

    @Override
    public void insertNotice(Notice n) {
        noticeRepository.insertNotice(n);

    }

    @Override
    public Notice selectNoticeView(int nNo) {
        return null;
    }

    @Override
    public void deleteNotice(int nNo) {
    }

    @Override
    public void updateNotice(Notice n) {
    }
}
