package com.refactoring.ilgusi.domain.service.dto;

import com.refactoring.ilgusi.domain.service.ServiceItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicePageDto {
	
	private List<ServiceItem> serviceList;
	private int reqPage;
	private int totalCount;
	private int totalPage;
	private String pageNavi;

	private int start;
	private int end;

	private String order;
	private String keyword;

}
