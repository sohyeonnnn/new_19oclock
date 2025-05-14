package com.refactoring.ilgusi.domain.chat;

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
	@Column(nullable=false)
	private int serviceNo;
	@Column(nullable=false)
	private String userId;
	@Column(nullable=false)
	private String freelancerId;
}
