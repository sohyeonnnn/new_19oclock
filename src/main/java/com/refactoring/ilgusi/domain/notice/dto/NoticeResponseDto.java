package com.refactoring.ilgusi.domain.notice.dto;

import com.refactoring.ilgusi.domain.notice.Notice;
import lombok.*;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeResponseDto {
	private List<Notice> noticeList;
	private int reqPage;
	private int totalCount;
	private int totalPage;
	private String pageNavi;
}
