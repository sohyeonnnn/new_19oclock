package com.refactoring.ilgusi.domain.notice.dto;

import com.refactoring.ilgusi.domain.notice.Notice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeViewDto {
	private Integer noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private MultipartFile file;
	private String filename;
	private String filepath;
	private String writeDate;

	public Notice toEntity() {
		return Notice.builder()
				.noticeTitle(noticeTitle)
				.noticeContent(noticeContent)
				.filename(filename)
				.filepath(filepath)
				.build();
	}

}
