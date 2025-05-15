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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_review_seq_gen")
	@SequenceGenerator(name = "service_review_seq_gen", sequenceName = "SERVICE_REVIEW_SEQ", allocationSize = 1)
	private Integer reviewNo;			//리뷰 고유 번호
	@Column
	private int tradeNo;			//거래 번호
	@Column
	private String reviewContent;	//리뷰내용
	@Column
	private int reviewRate;			//점수
	@Column
	private String writeDate;	//작성날짜
	@Transient
	private int rNum;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_no")
	private ServiceItem service;


	//줄바꿈
	public String getRContentBr() {
		return reviewContent.replaceAll("\r\n", "<br>");
	}


}
