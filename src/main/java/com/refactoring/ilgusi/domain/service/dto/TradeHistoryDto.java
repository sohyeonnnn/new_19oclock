package com.refactoring.ilgusi.domain.service.dto;

import lombok.Data;

@Data
public class TradeHistoryDto {
	private int tradeNo;			//거래번호
	private int serviceNo;			//서비스 번호
	private int memberNo;			//멤버 번호
	private int tradeStatus;		//거래 진행 상태
	private int tradePrice;			//거래 가격
	private String startDate;	//거래시작 날짜
	private String endDate;		//거래마감 날짜
	private String serviceTitle;
	private String memberId;
}
