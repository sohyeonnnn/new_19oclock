package com.refactoring.ilgusi.domain.request.dto;

import com.refactoring.ilgusi.domain.request.Request;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class RequestPageDto {
	private ArrayList<Request> list;
	private String pageNavi;
	private int totalCount;
	
}
