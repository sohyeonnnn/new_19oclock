package com.refactoring.ilgusi.domain.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDto {

	private int reviewNo; // 리뷰 고유 번호
	private int tradeNo; // 거래 번호
	private int serviceNo; // 서비스 번호
	private String serviceTitle;
	private String memberId; // 멤버아이디(작성자
	private String reviewContent; // 리뷰내용
	private int reviewRate; // 점수
	private String writeDate; // 작성날짜
	private int rNum;

	// 줄바꿈
	public String getRContentBr() {
		return reviewContent.replaceAll("\r\n", "<br>");
	}
}
