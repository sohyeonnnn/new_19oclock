package com.refactoring.ilgusi.domain.member.dto;

import com.refactoring.ilgusi.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberSearchIdPwDto {
	private String mId;
	private String mName;
	private String mPhone;

	public Member toEntity() {
		return Member.builder()
				.mId(mId)
				.mName(mName)
				.mPhone(mPhone)
				.build();
	}

}
