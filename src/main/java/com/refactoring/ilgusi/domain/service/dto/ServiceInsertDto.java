package com.refactoring.ilgusi.domain.service.dto;

import com.refactoring.ilgusi.domain.service.ServiceItem;
import lombok.*;

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
	private String brandName;  //브랜드명
	//private List<ServiceFile> fileList = new ArrayList<>(); //list로 해준것은 introduce.jsp에서 <for each list로 받아야한다.
	private String deleteStatus;
	private String adminApproval;

	public ServiceItem toEntity() {
		return ServiceItem.builder()
				.memberNo(memberNo)
				.serviceTitle(serviceTitle)
				.serviceContent(serviceContent)
				.serviceArea(serviceArea)
				.servicePrice(servicePrice)
				.mainCategory(mainCategory)
				.subCategory(subCategory)
				.workingDate(workingDate)
				.serviceImg(serviceImg)
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
				//", fileList=" + fileList.toString() +
				", deleteStatus='" + deleteStatus + '\'' +
				", adminApproval='" + adminApproval + '\'' +
				'}';
	}
}
