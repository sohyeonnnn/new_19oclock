package com.refactoring.ilgusi.domain.chat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
public class ChatContent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ccNo;			//채팅내용 고유번호
	@Column(nullable=false)
	private int cNo;			//채팅 번호
	@Column(nullable=false)
	private String mId;			//보낸 사람 아이디
	@Column(nullable=false)
	private String cDate;
	@Column(nullable=false)
	private String cTime;		//보낸 시간
	@Column(nullable=false)
	private String cContent;	//채팅 내용
	@Column(nullable=false)
	private int readStatus;		//읽음 여부

	//줄바꿈
	public String getCContentBr() {
		return cContent.replaceAll("\r\n", "<br>");
	}

}
