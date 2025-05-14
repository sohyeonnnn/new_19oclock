package com.refactoring.ilgusi.domain.service.dto;

import com.refactoring.ilgusi.domain.service.Service;
import com.refactoring.ilgusi.domain.service.ServiceFile;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceInsertDto {
	private int memberNo;
	private String serviceTitle;
	private String serviceContent;
	private String serviceArea;
	private int servicePrice;
	private int mainCategory;
	private int subCategory;
	private int workingDate;
	private String serviceImg;
	private int serviceRate;
	/*private String mName;
	private String mEmail;
	private String mPhone;
	private String introduce;
	private String enrollDate;*/
	private String brandName;  //브랜드명
/*	private String contactTime;//연락가능시간
	private List<Service> serviceList;		 //list로 해준것은 introduce.jsp에서 <for each list로 받아야한다.
	private List<ReviewDto> reviewList;  //list로 해준것은 introduce.jsp에서 <for each list로 받아야한다.
	private String pageNavi;				//페이징처리를위한 것*/
	private List<ServiceFile> fileList ; //list로 해준것은 introduce.jsp에서 <for each list로 받아야한다.
	private String deleteStatus;
	private String adminApproval;

	// 줄바꿈
/*	public String getIntroduceBr() {
		return introduce.replaceAll("\r\n", "<br>");
	}*/

	public Service toEntity() {
		return Service.builder()
				.memberNo(memberNo)
				.serviceTitle(serviceTitle)
				.serviceContent(serviceContent)
				.serviceArea(serviceArea)
				.servicePrice(servicePrice)
				.mainCategory(mainCategory)
				.subCategory(subCategory)
				.workingDate(workingDate)
				.serviceRate(serviceRate)
				.deleteStatus(deleteStatus)
				.adminApproval(adminApproval)
				.build();
	}

	@Override
	public String toString() {
		return "ServiceInsertDto{" +
				"memberNo=" + memberNo +
				", serviceTitle='" + serviceTitle + '\'' +
				", serviceContent='" + serviceContent + '\'' +
				", serviceArea='" + serviceArea + '\'' +
				", servicePrice=" + servicePrice +
				", mainCategory=" + mainCategory +
				", subCategory=" + subCategory +
				", workingDate=" + workingDate +
				", serviceImg='" + serviceImg + '\'' +
				", serviceRate=" + serviceRate +
				", brandName='" + brandName + '\'' +
				", deleteStatus='" + deleteStatus + '\'' +
				", adminApproval='" + adminApproval + '\'' +
				'}';
	}
}
