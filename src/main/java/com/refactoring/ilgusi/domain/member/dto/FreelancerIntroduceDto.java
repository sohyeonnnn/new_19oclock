package com.refactoring.ilgusi.domain.member.dto;

import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.service.ServiceItem;
import com.refactoring.ilgusi.domain.service.dto.ServiceInfoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FreelancerIntroduceDto {
	private int memberNo;
	private String memberId;
	private String brandName;
	private String email;
	private String phoneNo;
	private String introduce;
	private String contactTime;//연락가능시간

	private List<ServiceItem> serviceList;

	public static FreelancerIntroduceDto from(Member member) {
		return new FreelancerIntroduceDto(
				member.getMemberNo(),
				member.getMemberId(),
				member.getBrandName(),
				member.getEmail(),
				member.getPhoneNo(),
				member.getIntroduce(),
				member.getContactTime(),
				member.getServiceList()
		);
	}

	public int getServiceCount() {
		return serviceList.size();
	}

	public double getServiceRateAvg() {
		double sum = 0;
		for (ServiceItem item : serviceList){
			sum+=item.getServiceRate();
		}
		return sum/getServiceCount();
	}


/*
	private String mainCategoryName;
	private String subCategoryName;*/

	//private List<ReviewDto> reviewList;

	/*private String pageNavi;				//페이징처리를위한 것



	// 줄바꿈
	/*public String getIntroduceBr() {
		return introduce.replaceAll("\r\n", "<br>");
	}
*/



}
