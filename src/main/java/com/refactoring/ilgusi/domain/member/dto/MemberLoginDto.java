package com.refactoring.ilgusi.domain.member.dto;

import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.member.RoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberLoginDto {
	private Integer mNo;
	private String mId;
	private String mPw;

	public Member toEntity() {
		return Member.builder()
				.mId(mId)
				.mPw(mPw)
				.build();
	}

	@Override
	public String toString() {
		return "MemberLoginDto{" +
				"mNo=" + mNo +
				", mId='" + mId + '\'' +
				", mPw='" + mPw + '\'' +
				'}';
	}
}
