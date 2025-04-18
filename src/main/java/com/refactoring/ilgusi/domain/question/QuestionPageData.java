package com.refactoring.ilgusi.domain.question;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class QuestionPageData {
	private ArrayList<Question> list;
	private String pageNavi;
}
