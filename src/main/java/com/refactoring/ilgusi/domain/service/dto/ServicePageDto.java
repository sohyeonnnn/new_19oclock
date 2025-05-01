package com.refactoring.ilgusi.domain.service.dto;

import com.refactoring.ilgusi.domain.service.Service;
import lombok.Data;

import java.util.ArrayList;
@Data
public class ServicePageDto {
	
	private ArrayList<Service> list;
	private String pageNavi;
	
	private int end;
	private int start;
	private int reqPage;
	private int totalCount;
	private String order;
	private String keyword;
	private int cNo;
	
	
	
	public ServicePageDto(ArrayList<Service> list, String pageNavi, int end, int start, int reqPage, int totalCount,
						  String order, String keyword) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
		this.end = end;
		this.start = start;
		this.reqPage = reqPage;
		this.totalCount = totalCount;
		this.order = order;
		this.keyword = keyword;
	}



	public ServicePageDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
