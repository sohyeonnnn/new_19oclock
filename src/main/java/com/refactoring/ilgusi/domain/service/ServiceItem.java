package com.refactoring.ilgusi.domain.service;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "service")
public class ServiceItem {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_seq_gen")
	@SequenceGenerator(name = "service_seq_gen", sequenceName = "SERVICE_SEQ", allocationSize = 1)
	private Integer serviceNo;

	@Column
	private int memberNo;

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
	private String writeDate;

	@Column
	private String deleteStatus;

	@Column
	private String adminApproval;

	@Column
	private String serviceImg;

	@Builder.Default
	@OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ServiceFile> fileList = new ArrayList<>();

	public void addFile(ServiceFile file) {
		fileList.add(file);
		file.setService(this);
	}

	@Override
	public String toString() {
		return "ServiceItem{" +
				"serviceNo=" + serviceNo +
				", memberNo=" + memberNo +
				", serviceTitle='" + serviceTitle + '\'' +
				", servicePrice=" + servicePrice +
				", serviceContent='" + serviceContent + '\'' +
				", serviceArea='" + serviceArea + '\'' +
				", serviceRate=" + serviceRate +
				", mainCategory=" + mainCategory +
				", subCategory=" + subCategory +
				", workingDate=" + workingDate +
				", workingCount=" + workingCount +
				", writeDate='" + writeDate + '\'' +
				", deleteStatus='" + deleteStatus + '\'' +
				", adminApproval='" + adminApproval + '\'' +
				", serviceImg='" + serviceImg + '\'' +
				", fileList=" + fileList +
				'}';
	}
}
