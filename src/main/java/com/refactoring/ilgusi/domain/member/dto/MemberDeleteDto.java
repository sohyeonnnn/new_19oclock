package com.refactoring.ilgusi.domain.member.dto;

import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.member.RoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDeleteDto {
	private String mId;
	private String mPw;
	private String mName;
	private String mEmail;
	private String mPhone;
	private String data;
	private String object;

	public Member toEntity() {
		return Member.builder()
				.mId(mId)
				.mPw(mPw)
				.mName(mName)
				.mEmail(mEmail)
				.mPhone(mPhone)
				.mGrade(RoleEnum.USER)
				.build();
	}

}
