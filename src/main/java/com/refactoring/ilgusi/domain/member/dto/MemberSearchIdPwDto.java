package com.refactoring.ilgusi.domain.member.dto;

import com.refactoring.ilgusi.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberSearchIdPwDto {
	private String memberId;
	private String memberName;
	private String phoneNo;

	public Member toEntity() {
		return Member.builder()
				.memberId(memberId)
				.memberName(memberName)
				.phoneNo(phoneNo)
				.build();
	}

}
