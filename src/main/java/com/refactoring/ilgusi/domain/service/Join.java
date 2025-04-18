package com.refactoring.ilgusi.domain.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Join {
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
	private List<ReviewJoin> reviewList;  //list로 해준것은 introduce.jsp에서 <for each list로 받아야한다.
	private String pageNavi;				//페이징처리를위한 것
	private ArrayList<ServiceFile> fileList ; //list로 해준것은 introduce.jsp에서 <for each list로 받아야한다.
	private String sImg;
	private String deleteStatus;
	private String adminApproval;

	// 줄바꿈
	public String getIntroduceBr() {
		return introduce.replaceAll("\r\n", "<br>");
	}
	
}
