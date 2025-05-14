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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_seq_gen")
	@SequenceGenerator(name = "request_seq_gen", sequenceName = "REQUEST_SEQ", allocationSize = 1)
	private int requestNo;			//의뢰 번호
	@Column
	private String memberId;			//의뢰 작성자 ID
	@Column
	private String requestTitle;	//의뢰 타이틀
	@Column
	private String requestContent;	//의뢰 내용
	@Column
	private int requestStatus;		//의뢰 상태
	@Column
	private String filename;	//파일 이름
	@Column
	private String filepath;	//파일 경로
	@Column
	private String writeDate;	//작성일
	
	//줄바꿈
	public String getReqContentBr() {
		return requestContent.replaceAll("\r\n", "<br>");
	}
}
