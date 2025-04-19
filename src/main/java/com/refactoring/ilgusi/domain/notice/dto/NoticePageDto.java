package com.refactoring.ilgusi.domain.notice.dto;

import com.refactoring.ilgusi.domain.notice.Notice;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class NoticePageDto {
	private ArrayList<Notice> list;
	private String pageNavi;
}
