package com.refactoring.ilgusi.domain.notice;

import com.refactoring.ilgusi.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notice extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nNo;
	@Column(nullable=false)
	private String nTitle;
	@Column(nullable=false)
	private String nContent;
	@Column
	private String filename;
	@Column
	private String filepath;

	//줄바꿈
	public String getNContentBr() {
		return nContent.replaceAll("\r\n", "<br>");
	}

	@Transient
	public String getWriteDate() {
		if (getLastModifiedDate() != null) {
			return getLastModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}
		return "";
	}
}
