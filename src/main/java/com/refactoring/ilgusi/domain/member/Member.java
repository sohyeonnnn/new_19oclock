package com.refactoring.ilgusi.domain.member;

import com.refactoring.ilgusi.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

@Getter
@Setter
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
