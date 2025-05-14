package com.refactoring.ilgusi.domain.member.dto;

import com.refactoring.ilgusi.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FreelancerUpdateDto {
	private String memberNo;
	private String brandName;
	private String contactTime;
	private String introduce;

	public Member toEntity() {
		return Member.builder()
				.memberNo(Integer.valueOf(memberNo))
				.brandName(brandName)
				.contactTime(contactTime)
				.introduce(introduce)
				.build();
	}

	@Override
	public String toString() {
		return "FreelancerUpdateDto{" +
				"memberNo=" + memberNo +
				", brandName='" + brandName + '\'' +
				", contactTime='" + contactTime + '\'' +
				", introduce='" + introduce + '\'' +
				'}';
	}
}
