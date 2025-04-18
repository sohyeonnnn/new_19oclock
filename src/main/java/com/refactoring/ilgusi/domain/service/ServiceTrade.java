package com.refactoring.ilgusi.domain.service;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class ServiceTrade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tNo;			//거래번호
	private int sNo;			//서비스 번호
	private int mNo;			//멤버 번호
	private int tStatus;		//거래 진행 상태
	private int tPrice;			//거래 가격
	private String startDate;	//거래시작 날짜
	private String endDate;		//거래마감 날짜
	
	//(문정) 천원단위 나누기위함
	private String tPriceTxt;   //천원단위로 ,찍혀있는 텍스트
	private String mId;         //거래내역에서 아이디 뽑아오기위함
}
