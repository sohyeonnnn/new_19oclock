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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cNo;
	@Column(nullable=false)
	private int sNo;
	@Column(nullable=false)
	private String userId;
	@Column(nullable=false)
	private String freelancerId;
}
