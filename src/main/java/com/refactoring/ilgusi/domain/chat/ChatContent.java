package com.refactoring.ilgusi.domain.chat;

import com.refactoring.ilgusi.domain.member.Member;
import jdk.nashorn.internal.runtime.regexp.joni.encoding.CharacterType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
public class ChatContent {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chatcontent_seq_gen")
	@SequenceGenerator(name = "chatcontent_seq_gen", sequenceName = "CHATCONTENT_SEQ", allocationSize = 1)
	private int chatContentNo;			//채팅내용 고유번호
	@Column(nullable=false)
	private String chatDate;
	@Column(nullable=false)
	private String chatTime;		//보낸 시간
	@Column(nullable=false)
	private String chatContent;	//채팅 내용
	@Column(nullable=false)
	private int readStatus;		//읽음 여부


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chat_no")
	private Chat chat;			//채팅 번호
	public void setChat(Chat chat) {
		this.chat = chat;
	}


	//줄바꿈
	public String getCContentBr() {
		return chatContent.replaceAll("\r\n", "<br>");
	}

}
