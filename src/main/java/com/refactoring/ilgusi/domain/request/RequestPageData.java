package com.refactoring.ilgusi.domain.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class RequestPageData {
	private ArrayList<Request> list;
	private String pageNavi;
	private int totalCount;
	
}
