package com.refactoring.ilgusi.infrastructure.repository.jpa.notice;

import com.refactoring.ilgusi.domain.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpringDataNoticeRepository extends JpaRepository<Notice, Integer> {

    @Query(value = "SELECT * FROM notice WHERE (:keyword IS NULL OR n_title LIKE %:keyword%) ORDER BY n_no DESC LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Notice> findByKeywordWithRange(
            @Param("offset") int offset,
            @Param("limit") int limit,
            @Param("keyword") String keyword);

    @Query("SELECT COUNT(n) FROM Notice n WHERE n.nTitle LIKE %:keyword%")
    int countByKeyword(@Param("keyword") String keyword);

    void deleteBynNo(int nNo);


    @Modifying
    @Query("UPDATE Notice n SET n.nTitle = :nTitle, n.nContent = :nContent, n.filename = :filename, n.filepath = :filepath WHERE n.nNo = :nNo")
    void updateNotice(
            @Param("nNo") int nNo,
            @Param("nTitle") String nTitle,
            @Param("nContent") String nContent,
            @Param("filename") String filename,
            @Param("filepath") String filepath
    );

}
