package com.refactoring.ilgusi.domain.member.dto;

import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.member.RoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberJoinDto{
	private String memberId;
	private String memberPw;
	private String memberName;
	private String email;
	private String phoneNo;

	public Member toEntity() {
		return Member.builder()
				.memberId(memberId)
				.memberPw(memberPw)
				.memberName(memberName)
				.email(email)
				.phoneNo(phoneNo)
				.memberGrade(RoleEnum.USER)
				.build();
	}

}
