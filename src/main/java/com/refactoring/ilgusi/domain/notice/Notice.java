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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notice_seq_gen")
	@SequenceGenerator(name = "notice_seq_gen", sequenceName = "NOTICE_SEQ", allocationSize = 1)
	private Integer noticeNo;
	@Column(nullable=false)
	private String noticeTitle;
	@Lob
	@Column(nullable=false)
	private String noticeContent;
	@Column
	private String filename;
	@Column
	private String filepath;

	@Transient
	public String getWriteDate() {
		if (getLastModifiedDate() != null) {
			return getLastModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}
		return "";
	}
}
