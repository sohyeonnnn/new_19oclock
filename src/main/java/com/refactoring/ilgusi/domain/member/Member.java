package com.refactoring.ilgusi.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.refactoring.ilgusi.common.BaseEntity;
import com.refactoring.ilgusi.domain.chat.Chat;
import com.refactoring.ilgusi.domain.favorite.Favorite;
import com.refactoring.ilgusi.domain.service.ServiceItem;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@ToString(exclude = "serviceList")
public class Member extends BaseEntity {
	@Id
	@Column(name = "MEMBER_NO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_gen")
	@SequenceGenerator(name = "member_seq_gen", sequenceName = "MEMBER_SEQ", allocationSize = 1)
	private Integer memberNo;
	@Column(nullable=false)
	private String memberId;
	@Column(nullable=false)
	private String memberPw;
	@Column(nullable=false)
	private String memberName;
	@Column(nullable=false)
	private String email;
	@Column
	private String phoneNo;
	@Enumerated(EnumType.STRING)
	@Column
	private RoleEnum memberGrade;
	@Column
	private Integer warningCount;
	@Column
	private String introduce;
	@Column
	@CreatedDate
	private String enrollDate;
	@Column
	private String brandName;
	@Column
	private String contactTime;
	@Column
	private Integer buyingCount;
	@Column
	private Integer sellingCount;

	@JsonIgnore
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ServiceItem> serviceList = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Favorite> favoriteList = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Chat> chatList = new ArrayList<>();








	// 줄바꿈
	public String getIntroduceBr() {
		return introduce.replaceAll("\r\n", "<br>");
	}

	@Override
	public String toString() {
		return "Member{" +
				"memberNo=" + memberNo +
				", memberId='" + memberId + '\'' +
				", memberPw='" + memberPw + '\'' +
				", memberName='" + memberName + '\'' +
				", email='" + email + '\'' +
				", phoneNo='" + phoneNo + '\'' +
				", memberGrade=" + memberGrade +
				", warningCount=" + warningCount +
				", introduce='" + introduce + '\'' +
				", enrollDate='" + enrollDate + '\'' +
				", brandName='" + brandName + '\'' +
				", contactTime='" + contactTime + '\'' +
				", buyingCount=" + buyingCount +
				", sellingCount=" + sellingCount +
				'}';
	}

	@Builder
	public Member(Integer memberNo, String memberId, String memberPw, String memberName, String email, String phoneNo, RoleEnum memberGrade, String brandName, String contactTime, String introduce) {
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.email = email;
		this.phoneNo = phoneNo;
		this.memberGrade = memberGrade;
		this.brandName = brandName;
		this.contactTime = 	contactTime;
		this.introduce = introduce;
	}

	public void setPw(String newPw){
		memberPw = newPw;
	}

	public void setGrade(RoleEnum newGrade){
		memberGrade = newGrade;
	}

	public void setBuyingCount(int newBuyingCount){
		buyingCount = newBuyingCount;
	}

	public void setSellingCount(int newSellingCount){
		sellingCount = newSellingCount;
	}


	public boolean isAdmin(){
		return memberGrade.equals(RoleEnum.ADMIN);
	}

	public boolean isFreelancer(){
		return memberGrade.equals(RoleEnum.FREELANCER);
	}

	public boolean isUser(){
		return memberGrade.equals(RoleEnum.USER);
	}
	//jpa사용시 - 엔티티 객체가 persist(저장)되기 전에 자동으로 실행
	/*@PrePersist
	public void setDefault() {
		// 비밀번호 암호화
		this.mPw = CommonUtil.encrypt(this.mPw);
		// 기본 등급 설정
		if (this.mGrade == null) {
			this.mGrade = RoleEnum.USER;
		}
	}*/

}
