package com.refactoring.ilgusi.domain.member.dto;

import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.member.RoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FreelancerUpdateDto {
	//private Integer mNo;
	private String mId;
	private String brandName;
	private String contactTime;
	private String introduce;

	public Member toEntity() {
		return Member.builder()
				//.mNo(mNo)
				.mId(mId)
				.brandName(brandName)
				.contactTime(contactTime)
				.introduce(introduce)
				.build();
	}

	@Override
	public String toString() {
		return "FreelancerUpdateDto{" +
				//"mNo=" + mNo +
				", mId='" + mId + '\'' +
				", brandName='" + brandName + '\'' +
				", contactTime='" + contactTime + '\'' +
				", introduce='" + introduce + '\'' +
				'}';
	}
}
