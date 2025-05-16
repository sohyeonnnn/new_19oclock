package com.refactoring.ilgusi.domain.member.dto;

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

	private String memberName;
	private String email;
	private String phoneNo;
	private String introduce;
	private String contactTime;//연락가능시간

	private List<ServiceInfoDto> serviceItemList;
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
