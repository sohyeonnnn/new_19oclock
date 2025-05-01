package com.refactoring.ilgusi.domain.service.dto;

import com.refactoring.ilgusi.domain.service.Service;
import com.refactoring.ilgusi.domain.service.ServiceFile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ServiceInsertDto {
	private String mId;
	private String sTitle;
	private int sPrice;
	private String sContent;
	private String sArea;
	private int mainCategory;
	private int subCategory;
	private int workingDate;
	private int mNo;  
	private int sRate;
	private String mName;
	private String mEmail;
	private String mPhone;
	private String introduce;  
	private String enrollDate;
	private String brandName;  //브랜드명
	private String contactTime;//연락가능시간
	private List<Service> serviceList;		 //list로 해준것은 introduce.jsp에서 <for each list로 받아야한다.
	private List<ReviewDto> reviewList;  //list로 해준것은 introduce.jsp에서 <for each list로 받아야한다.
	private String pageNavi;				//페이징처리를위한 것
	private ArrayList<ServiceFile> fileList ; //list로 해준것은 introduce.jsp에서 <for each list로 받아야한다.
	private String sImg;
	private String deleteStatus;
	private String adminApproval;

	// 줄바꿈
	public String getIntroduceBr() {
		return introduce.replaceAll("\r\n", "<br>");
	}

	public Service toEntity() {
		return Service.builder()
				.mNo(mNo)
				.mainCategory(mainCategory)
				.subCategory(subCategory)
				.sArea(sArea)
				.sPrice(sPrice)
				.workingDate(workingDate)
				.sContent(sContent)
				.brandName(brandName)
				.build();
	}

	@Override
	public String toString() {
		return "Join{" +
				"mId='" + mId + '\'' +
				", sTitle='" + sTitle + '\'' +
				", sPrice=" + sPrice +
				", sContent='" + sContent + '\'' +
				", sArea='" + sArea + '\'' +
				", mainCategory=" + mainCategory +
				", subCategory=" + subCategory +
				", workingDate=" + workingDate +
				", mNo=" + mNo +
				", sRate=" + sRate +
				", mName='" + mName + '\'' +
				", mEmail='" + mEmail + '\'' +
				", mPhone='" + mPhone + '\'' +
				", introduce='" + introduce + '\'' +
				", enrollDate='" + enrollDate + '\'' +
				", brandName='" + brandName + '\'' +
				", contactTime='" + contactTime + '\'' +
				", serviceList=" + serviceList +
				", reviewList=" + reviewList +
				", pageNavi='" + pageNavi + '\'' +
				", fileList=" + fileList +
				", sImg='" + sImg + '\'' +
				", deleteStatus='" + deleteStatus + '\'' +
				", adminApproval='" + adminApproval + '\'' +
				'}';
	}
}
