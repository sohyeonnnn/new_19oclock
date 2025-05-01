package com.refactoring.ilgusi.domain.service.dto;

import com.refactoring.ilgusi.domain.service.ServiceReview;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class ReviewPageDto {
	
	private ArrayList<ServiceReview> list;
	private String pageNavi;
	
	
	
	
}
