package com.refactoring.ilgusi.domain.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.NumberFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceInfoDto {
	private int serviceNo; // 서비스 번호
	private String serviceTitle; // 서비스 제목
	private String serviceContent; // 서비스 내용
	private int servicePrice; // 서비스 가격
	private String serviceArea; // 서비스 지역
	private String serviceImg; // 섬네일 이미지
	private float serviceRate; // 서비스 평점
	private int mainCategory; // 메인카테고리 번호
	private int subCategory; // 서브카테고리 번호
	private int workingDate; // 예상 작업일
	private int workingCount; // 진행한 작업 수
	private String writeDate; // 작성 날짜
	private String deleteStatus; // 삭제 여부
	private String adminApproval; // 승인 여부

	private String memberId;
	private String brandName; // 브랜드이름
	private String introduce;
	private String mainCategoryName; //메인카테고리이름
	private String subCategoryName; //서브카테고리이름

	public String getServicePriceTxt() {
		return NumberFormat.getInstance().format(servicePrice);
	}

	//private List<ServiceFile> fileList = new ArrayList<>();

	//private List<ServiceReview> reviewList;

}
