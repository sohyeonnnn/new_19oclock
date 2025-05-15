package com.refactoring.ilgusi.application.notice;

import com.refactoring.ilgusi.common.CommonEnum;
import com.refactoring.ilgusi.domain.notice.Notice;
import com.refactoring.ilgusi.domain.notice.dto.NoticeInsertDto;
import com.refactoring.ilgusi.domain.notice.dto.NoticePageDto;
import com.refactoring.ilgusi.domain.notice.dto.NoticeViewDto;
import com.refactoring.ilgusi.domain.notice.interfaces.NoticeRepository;
import com.refactoring.ilgusi.domain.notice.interfaces.NoticeService;
import com.refactoring.ilgusi.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;

    @Override
    public NoticePageDto selectNoticeListApi(int reqPage, String keyword) {
        int limit = 10;
        int offset = (reqPage - 1) * limit;

        List<Notice> noticeList = noticeRepository.selectNoticeList(offset, limit, keyword);
        int totalCount = noticeRepository.totalCount(keyword);
        int totalPage = (int) Math.ceil((double) totalCount / limit);
        String pageNavi = this.generatePageNavi(reqPage, keyword, totalPage, totalCount, limit);

        return NoticePageDto.builder()
                .noticeList(noticeList)
                .reqPage(reqPage)
                .totalCount(totalCount)
                .totalPage(totalPage)
                .pageNavi(pageNavi)
                .build();
    }

    public String generatePageNavi(int reqPage, String keyword, int totalPage, int totalCount, int limit) {
        //페이지 네비
        int pageNaviSize = 5;
        int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize +1;
        StringBuffer pageNavi = new StringBuffer();

        pageNavi.append("<ul class='pagination justify-content-center'>");

        // 이전 버튼
        if (pageNo != 1) {
            pageNavi.append("<li class='page-item'><a class='page-link' href='javascript:void(0)' onclick='loadNotices(" + (pageNo - 1) + ", `" + keyword + "`)'>pre</a></li>");
        }

        // 페이지 네비 버튼
        for (int i = 0; i < pageNaviSize; i++) {
            if (reqPage == pageNo) {
                pageNavi.append("<li class='page-item'><span class='page-link selected'>" + pageNo + "</span></li>");
            } else {
                pageNavi.append("<li class='page-item'><a class='page-link' href='javascript:void(0)' onclick='loadNotices(\" + (pageNo - 1) + \", `\" + keyword + \"`)'>" + pageNo + "</a></li>");
            }
            pageNo++;

            if (pageNo > totalPage) {
                break;
            }
        }

        // 다음 버튼
        if (reqPage <= (totalPage / pageNaviSize)) {
            pageNavi.append("<li class='page-item'><a class='page-link' href='javascript:void(0)' onclick='loadNotices(" + (pageNo - 1) + ", `" + keyword + "`)'>next</a></li>");
        }

        // 글이 limit 이하면 페이지 네비 필요 없음
        if (totalCount <= limit) {
            pageNavi.append("</ul>");
        }
        return pageNavi.toString();
    }

    @Override
    public void insertNotice(NoticeInsertDto notice) {
        noticeRepository.insertNotice(notice.toEntity());
    }

    @Override
    public NoticeViewDto selectNoticeView(int noticeNo) {
        return noticeRepository.selectNoticeView(noticeNo)
                .map(notice -> NoticeViewDto.builder()
                        .noticeNo(notice.getNoticeNo())
                        .noticeTitle(notice.getNoticeTitle())
                        .noticeContent(notice.getNoticeContent())
                        .filename(notice.getFilename())
                        .filepath(notice.getFilepath())
                        .writeDate(notice.getWriteDate())
                        .build())
                .orElseThrow((() -> new CustomException(CommonEnum.NOT_VALID_NOTICE.getVal(),"/",true)));
    }

    @Override
    public void deleteNotice(int noticeNo) {
        noticeRepository.deleteNotice(noticeNo);
    }

    @Override
    public void updateNotice(Notice notice) {
        noticeRepository.updateNotice(notice);
    }
}
