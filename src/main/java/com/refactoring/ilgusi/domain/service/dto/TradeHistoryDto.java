package com.refactoring.ilgusi.domain.service.dto;

import lombok.Data;

@Data
public class TradeHistoryDto {
	private int tNo;			//거래번호
	private int sNo;			//서비스 번호
	private int mNo;			//멤버 번호
	private int tStatus;		//거래 진행 상태
	private int tPrice;			//거래 가격
	private String startDate;	//거래시작 날짜
	private String endDate;		//거래마감 날짜
	private String sTitle;
	private String mId;
}
