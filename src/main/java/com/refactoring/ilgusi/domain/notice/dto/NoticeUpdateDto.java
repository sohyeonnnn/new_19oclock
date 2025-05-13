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
public class NoticeUpdateDto {
	private Integer noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private MultipartFile file;
	private String filename;
	private String filepath;

	public Notice toEntity() {
		return Notice.builder()
				.noticeNo(noticeNo)
				.noticeTitle(noticeTitle)
				.noticeContent(noticeContent)
				.filename(filename)
				.filepath(filepath)
				.build();
	}



}
