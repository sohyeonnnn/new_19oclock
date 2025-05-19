package com.refactoring.ilgusi.domain.chat;

import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.service.ServiceItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Chat {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_seq_gen")
	@SequenceGenerator(name = "chat_seq_gen", sequenceName = "CHAT_SEQ", allocationSize = 1)
	private int chatNo;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_no")
	private ServiceItem service;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_no")
	private Member user;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "free_no")
	private Member freelancer;
}
