package com.refactoring.ilgusi.domain.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Request {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reqNo;			//의뢰 번호
	@Column
	private String mId;			//의뢰 작성자 ID
	@Column
	private String reqTitle;	//의뢰 타이틀
	@Column
	private String reqContent;	//의뢰 내용
	@Column
	private int reqStatus;		//의뢰 상태
	@Column
	private String filename;	//파일 이름
	@Column
	private String filepath;	//파일 경로
	@Column
	private String writeDate;	//작성일
	
	//줄바꿈
	public String getReqContentBr() {
		return reqContent.replaceAll("\r\n", "<br>");
	}
}
