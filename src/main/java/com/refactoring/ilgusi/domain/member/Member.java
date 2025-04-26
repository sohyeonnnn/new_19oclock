package com.refactoring.ilgusi.domain.member;

import com.refactoring.ilgusi.common.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Member extends BaseEntity {
	@Id
	@Column(name = "M_NO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer mNo;
	@Column(nullable=false)
	private String mId;
	@Column(nullable=false)
	private String mPw;
	@Column(nullable=false)
	private String mName;
	@Column(nullable=false)
	private String mEmail;
	@Column
	private String mPhone;
	@Enumerated(EnumType.STRING)
	@Column
	private RoleEnum mGrade;
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

	// 줄바꿈
	public String getIntroduceBr() {
		return introduce.replaceAll("\r\n", "<br>");
	}

	@Builder
	public Member(Integer mNo, String mId, String mPw, String mName, String mEmail, String mPhone, RoleEnum mGrade, String brandName, String contactTime, String introduce) {
		this.mNo = mNo;
		this.mId = mId;
		this.mPw = mPw;
		this.mName = mName;
		this.mEmail = mEmail;
		this.mPhone = mPhone;
		this.mGrade = mGrade;
		this.brandName = brandName;
		this.contactTime = 	contactTime;
		this.introduce = introduce;
	}

	public void changePw(String newPw){
		mPw = newPw;
	}

	public void changeGrade(RoleEnum newGrade){
		mGrade = newGrade;
	}

	public void changeBuyingCount(int newBuyingCount){
		buyingCount = newBuyingCount;
	}

	public void changeSellingCount(int newSellingCount){
		sellingCount = newSellingCount;
	}

	@Override
	public String toString() {
		return "Member{" +
				"mNo=" + mNo +
				", mId='" + mId + '\'' +
				", mPw='" + mPw + '\'' +
				", mName='" + mName + '\'' +
				", mEmail='" + mEmail + '\'' +
				", mPhone='" + mPhone + '\'' +
				", mGrade=" + mGrade +
				", warningCount=" + warningCount +
				", introduce='" + introduce + '\'' +
				", enrollDate='" + enrollDate + '\'' +
				", brandName='" + brandName + '\'' +
				", contactTime='" + contactTime + '\'' +
				", buyingCount=" + buyingCount +
				", sellingCount=" + sellingCount +
				'}';
	}

	public boolean isAdmin(){
		return mGrade.equals(RoleEnum.ADMIN);
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
