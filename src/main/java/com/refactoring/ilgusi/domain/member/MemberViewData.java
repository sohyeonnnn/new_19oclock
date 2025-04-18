package com.refactoring.ilgusi.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberViewData {
	private String grade;
	private String keyword;
	private String order;
	private int start;
	private int end;

	public MemberViewData(String grade, String keyword, String order, int start, int end) {
		super();
		this.grade = grade;
		this.keyword = keyword;
		this.order = order;
		this.start = start;
		this.end = end;
	}
}
