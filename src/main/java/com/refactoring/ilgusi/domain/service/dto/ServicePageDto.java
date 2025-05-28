package com.refactoring.ilgusi.domain.service.dto;

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
	private List<ServiceInfoDto> serviceList;
	private int reqPage;
	private int totalCount;
	private int totalPage;
	private String pageNavi;

	public int getServiceCount() {
		return serviceList.size();
	}

}
