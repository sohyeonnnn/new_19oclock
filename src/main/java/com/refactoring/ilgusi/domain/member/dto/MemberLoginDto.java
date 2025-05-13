package com.refactoring.ilgusi.domain.member.dto;

import com.refactoring.ilgusi.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberLoginDto {
	private Integer memberNo;
	private String memberId;
	private String memberPw;

	public Member toEntity() {
		return Member.builder()
				.memberId(memberId)
				.memberPw(memberPw)
				.build();
	}

	@Override
	public String toString() {
		return "MemberLoginDto{" +
				"memberNo=" + memberNo +
				", memberId='" + memberId + '\'' +
				", memberPw='" + memberPw + '\'' +
				'}';
	}
}
