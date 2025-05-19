package com.refactoring.ilgusi.domain.question;

import com.refactoring.ilgusi.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_seq_gen")
	@SequenceGenerator(name = "question_seq_gen", sequenceName = "QUESTION_SEQ", allocationSize = 1)
	private int questionNo;				//문의번호
	@Column
	private String questionTitle;			//문의 제목
	@Column
	private String questionContent;		//문의 내용
	@Column
	private int secretStatus;		//비밀여부
	@Column
	private String writeDate;		//작성 날짜
	@Column
	private int answerStatus;		//답변여부
	@Column
	private String answerContent;	//답변 내용
	@Column
	private String answerDate;		//답변 날짜
	@Column
	private String filePath;		//파일이름(물리적)

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberNo")
	private Member member;				//회원 번호(작성자)
	public void setMember(Member member) {this.member = member;}


	//줄바꿈
	public String getQContentBr() {
		return questionContent.replaceAll("\r\n", "<br>");
	}
	public String getAnswerContentBr() {
		return answerContent.replaceAll("\r\n", "<br>");
	}
}
