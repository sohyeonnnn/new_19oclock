package com.refactoring.ilgusi.domain.notice.dto;

import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.notice.Notice;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeInsertDto {
	private String title;
	private String content;
	private String filename;
	private String filepath;

	public Notice toEntity() {
		return Notice.builder()
				.nTitle(title)
				.nContent(content)
				.filename(filename)
				.filepath(filepath)
				.build();
	}

}
