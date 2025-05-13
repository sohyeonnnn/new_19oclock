package com.refactoring.ilgusi.infrastructure.repository.jpa.notice;

import com.refactoring.ilgusi.domain.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SpringDataNoticeRepository extends JpaRepository<Notice, Integer> {

    @Query(value = "SELECT * FROM notice WHERE (:keyword IS NULL OR notice_title LIKE %:keyword%) ORDER BY notice_no DESC LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Notice> findByKeywordWithRange(
            @Param("offset") int offset,
            @Param("limit") int limit,
            @Param("keyword") String keyword);

    @Query("SELECT COUNT(n) FROM Notice n WHERE n.noticeTitle LIKE %:keyword%")
    int countByKeyword(@Param("keyword") String keyword);

    void deleteByNoticeNo(int noticeNo);


    @Modifying
    @Query("UPDATE Notice n SET n.noticeTitle = :noticeTitle, n.noticeContent = :noticeContent, n.filename = :filename, n.filepath = :filepath WHERE n.noticeNo = :noticeNo")
    void updateNotice(
            @Param("noticeNo") int noticeNo,
            @Param("noticeTitle") String noticeTitle,
            @Param("noticeContent") String noticeContent,
            @Param("filename") String filename,
            @Param("filepath") String filepath
    );

    Optional<Notice> findByNoticeNo(int noticeNo);
}
