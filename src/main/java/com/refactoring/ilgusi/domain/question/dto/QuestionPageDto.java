package com.refactoring.ilgusi.domain.question.dto;

import com.refactoring.ilgusi.domain.question.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class QuestionPageDto {
	private ArrayList<Question> list;
	private String pageNavi;
}
