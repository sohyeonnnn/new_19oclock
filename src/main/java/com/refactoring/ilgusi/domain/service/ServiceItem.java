package com.refactoring.ilgusi.domain.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.refactoring.ilgusi.common.BaseEntity;
import com.refactoring.ilgusi.domain.favorite.Favorite;
import com.refactoring.ilgusi.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "service")
public class ServiceItem extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_seq_gen")
	@SequenceGenerator(name = "service_seq_gen", sequenceName = "SERVICE_SEQ", allocationSize = 1)
	private Integer serviceNo;

	@Column
	private String serviceTitle;

	@Column
	private int servicePrice;

	@Column
	private String serviceContent;

	@Column
	private String serviceArea;

	@Column
	private float serviceRate;

	@Column
	private int mainCategory;

	@Column
	private int subCategory;

	@Column
	private int workingDate;

	@Column
	private int workingCount;

	@Column
	@CreatedDate
	private String writeDate;

	@Column
	private String deleteStatus;

	@Column
	private String adminApproval;

	@Column
	private String serviceImg;

	@JsonIgnore
	@Builder.Default
	@OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ServiceReview> reviewList = new ArrayList<>();


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_no")
	private Member member;

	public void setMember(Member member) {
		this.member = member;
	}


	private String getStarString(double rate) {
		int fullStars = (int) rate;
		StringBuilder stars = new StringBuilder();
		for (int i = 0; i < fullStars; i++) stars.append("★");
		for (int i = fullStars; i < 5; i++) stars.append("☆");
		return stars.toString();
	}

}
