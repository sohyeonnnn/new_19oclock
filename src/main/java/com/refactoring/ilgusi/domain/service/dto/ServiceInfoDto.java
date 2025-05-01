package com.refactoring.ilgusi.domain.service.dto;

import lombok.Data;

@Data
public class ServiceInfoDto {

	private int sNo; // 서비스 번호
	private String mId; // 회원 아이디(작성자
	private String sTitle; // 서비스 제목
	private int sPrice; // 서비스 가격
	private String sContent; // 서비스 내용
	private String sArea; // 서비스 지역
	private String sImg; // 섬네일 이미지
	private int sRate; // 서비스 평점
	private int mainCategory; // 메인카테고리 번호
	private int subCategory; // 서브카테고리 번호
	private int workingDate; // 예상 작업일
	private int workingCount; // 진행한 작업 수
	private String writeDate; // 작성 날짜
	private char deleteStatus; // 삭제 여부
	private char adminApproval; // 승인 여부

	private String brandName; // 브랜드이름
	private String mCatName; //메인카테고리이름
	private String sCatName; //서브카테고리이름
}
