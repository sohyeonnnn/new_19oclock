package com.refactoring.ilgusi.domain.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Service {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sNo;			//서비스 번호
	@Column
	private String mId;			//회원 아이디(작성자
	@Column
	private String sTitle;		//서비스 제목
	@Column
	private int sPrice;			//서비스 가격
	@Column
	private String sContent;	//서비스 내용
	@Column
	private String sArea;		//서비스 지역
	@Column
	private String sImg;		//섬네일 이미지
	@Column
	private float sRate;			//서비스 평점
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
	private char deleteStatus;	//삭제 여부
	@Column
	private char adminApproval;	//승인 여부
	
	//(문정) 추가
	@Column
	private String mainCategoryName;
	@Column
	private String subCategoryName;
	@Column
	private String sPriceTxt;    //천원단위로 ,찍은 텍스트 저장
	
	//(다솜)추가
	@Column
	private String brandName;
	
	//줄바꿈
	public String getSContentBr() {
		return sContent.replaceAll("\r\n", "<br>");
	}
}
