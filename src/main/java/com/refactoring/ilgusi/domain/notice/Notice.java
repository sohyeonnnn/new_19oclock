package com.refactoring.ilgusi.domain.notice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Notice {
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
	@Column
	private String writeDate;
	
	//줄바꿈
	public String getNContentBr() {
		return nContent.replaceAll("\r\n", "<br>");
	}
}
