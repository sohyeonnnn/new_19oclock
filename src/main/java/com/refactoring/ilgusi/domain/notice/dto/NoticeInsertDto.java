package com.refactoring.ilgusi.domain.notice.dto;

import com.refactoring.ilgusi.domain.notice.Notice;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeInsertDto {
	private String nTitle;
	private String nContent;
	private MultipartFile file;
	private String filename;
	private String filepath;

	public Notice toEntity() {
		return Notice.builder()
				.nTitle(nTitle)
				.nContent(nContent)
				.filename(filename)
				.filepath(filepath)
				.build();
	}

}
