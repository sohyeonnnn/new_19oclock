package com.refactoring.ilgusi.domain.service;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class ServiceReview {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rNo;			//리뷰 고유 번호
	@Column
	private int tNo;			//거래 번호
	@Column
	private int sNo;			//서비스 번호
	@Column
	private String mId;			//멤버아이디(작성자
	@Column
	private String rContent;	//리뷰내용
	@Column
	private int rRate;			//점수
	@Column
	private String writeDate;	//작성날짜
	@Column
	private int rNum;
	
	//줄바꿈
	public String getRContentBr() {
		return rContent.replaceAll("\r\n", "<br>");
	}

	@Override
	public String toString() {
		return "ServiceReview{" +
				"rNo=" + rNo +
				", tNo=" + tNo +
				", sNo=" + sNo +
				", mId='" + mId + '\'' +
				", rContent='" + rContent + '\'' +
				", rRate=" + rRate +
				", writeDate='" + writeDate + '\'' +
				", rNum=" + rNum +
				'}';
	}
}
