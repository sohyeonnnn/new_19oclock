package com.refactoring.ilgusi.domain.service;

import lombok.Data;

@Data
public class ServiceReview {
	private int rNo;			//리뷰 고유 번호
	private int tNo;			//거래 번호
	private int sNo;			//서비스 번호
	private String mId;			//멤버아이디(작성자
	private String rContent;	//리뷰내용
	private int rRate;			//점수
	private String writeDate;	//작성날짜
	private int rNum;
	
	//줄바꿈
	public String getRContentBr() {
		return rContent.replaceAll("\r\n", "<br>");
	}
}
