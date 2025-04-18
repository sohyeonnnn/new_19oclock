package com.refactoring.ilgusi.domain.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class ReviewPageData {
	
	private ArrayList<ServiceReview> list;
	private String pageNavi;
	
	
	
	
}
