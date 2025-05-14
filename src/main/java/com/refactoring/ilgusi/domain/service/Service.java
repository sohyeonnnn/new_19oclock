package com.refactoring.ilgusi.domain.service;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Service {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_seq_gen")
	@SequenceGenerator(name = "service_seq_gen", sequenceName = "SERVICE_SEQ", allocationSize = 1)
	private Integer serviceNo;			//서비스 번호
	@Column
	private int memberNo;			//회원 아이디(작성자
	@Column
	private String serviceTitle;		//서비스 제목
	@Column
	private int servicePrice;			//서비스 가격
	@Column
	private String serviceContent;	//서비스 내용
	@Column
	private String serviceArea;		//서비스 지역
	@Column
	private String serviceImg;		//섬네일 이미지
	@Column
	private float serviceRate;			//서비스 평점
	@Column
	private int mainCategory;	//메인카테고리 번호
	@Column
	private int subCategory;	//서브카테고리 번호
	@Column
	private int workingDate;	//예상 작업일
	@Column
	private int workingCount;	//진행한 작업 수
	@Column
	private String writeDate;	//작성 날짜
	@Column
	private String deleteStatus;	//삭제 여부
	@Column
	private String adminApproval;	//승인 여부


	@Override
	public String toString() {
		return "Service{" +
				"serviceNo=" + serviceNo +
				", memberNo=" + memberNo +
				", serviceTitle='" + serviceTitle + '\'' +
				", servicePrice=" + servicePrice +
				", serviceContent='" + serviceContent + '\'' +
				", serviceArea='" + serviceArea + '\'' +
				", serviceImg='" + serviceImg + '\'' +
				", serviceRate=" + serviceRate +
				", mainCategory=" + mainCategory +
				", subCategory=" + subCategory +
				", workingDate=" + workingDate +
				", workingCount=" + workingCount +
				", writeDate='" + writeDate + '\'' +
				", deleteStatus='" + deleteStatus + '\'' +
				", adminApproval='" + adminApproval + '\'' +
				'}';
	}
}
