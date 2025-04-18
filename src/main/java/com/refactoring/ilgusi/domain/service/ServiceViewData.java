package com.refactoring.ilgusi.domain.service;

import lombok.Data;

@Data
public class ServiceViewData {
	private String status;
	private String keyword1;
	private String keyword2;
	private String order;
	private int start;
	private int end;

	public ServiceViewData(String status, String keyword1, String keyword2, String order, int start, int end) {
		super();
		this.status = status;
		this.keyword1 = keyword1;
		this.keyword2 = keyword2;
		this.order = order;
		this.start = start;
		this.end = end;
	}

}
