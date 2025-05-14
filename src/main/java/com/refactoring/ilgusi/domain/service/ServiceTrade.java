package com.refactoring.ilgusi.domain.service;

import com.refactoring.ilgusi.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class ServiceTrade extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_trade_seq_gen")
	@SequenceGenerator(name = "service_trade_seq_gen", sequenceName = "SERVICE_TRADE_SEQ", allocationSize = 1)
	private Integer tradeNo;			//거래번호
	@Column
	private int serviceNo;			//서비스 번호
	@Column(name = "MEMBER_NO")
	private int memberNo;			//멤버 번호
	@Column(name = "TRADE_STATUS")
	private int tradeStatus;		//거래 진행 상태
	@Column
	private int tradePrice;			//거래 가격
	@Column
	private String startDate;	//거래시작 날짜
	@Column
	private String endDate;		//거래마감 날짜
	
	//(문정) 천원단위 나누기위함
	private String tPriceTxt;   //천원단위로 ,찍혀있는 텍스트
	private String memberId;         //거래내역에서 아이디 뽑아오기위함
}
