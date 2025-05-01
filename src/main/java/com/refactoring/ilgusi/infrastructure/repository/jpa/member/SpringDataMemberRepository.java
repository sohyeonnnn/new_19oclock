package com.refactoring.ilgusi.infrastructure.repository.jpa.member;

import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.member.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

// 기술 전용 인터페이스, Spring이 구현체를 자동 생성해줌
// MemberRepository를 extends하지 않음
public interface SpringDataMemberRepository extends JpaRepository<Member, Integer>{
    Optional<Member> findBymId(String mId);
    Optional<Member> findBymNo(Integer mNo);

    void deleteBymNo(int mNo);

    @Query("SELECT m FROM Member m WHERE (m.mName = :mName AND m.mPhone = :mPhone) OR (m.mId = :mId AND m.mPhone = :mPhone)")
    Optional<Member> findBymNameAndmPhoneOrmIdAndmPhone(@Param("mName") String mName,
                                                    @Param("mPhone") String mPhone,
                                                    @Param("mId") String mId);

    @Modifying
    @Transactional
    @Query("UPDATE Member m SET m.mPw = :mPw WHERE m.mNo = :mNo")
    int changePw(@Param("mNo") int mNo, @Param("mPw") String mPw);

    @Modifying
    @Transactional
    @Query("UPDATE Member m SET m.mGrade = CASE WHEN m.mGrade = :user THEN :freelancer ELSE :user END  WHERE m.mNo = :mNo")
    int changeGrade(@Param("mNo") int mNo,
                    @Param("user") RoleEnum user,
                    @Param("freelancer") RoleEnum freelancer);

    @Modifying
    @Transactional
    @Query("UPDATE Member m SET m.mEmail = :data WHERE m.mId = :mNo")
    int changeMypageEmail(@Param("mNo") int mNo,
                     @Param("data") String data);

    @Modifying
    @Transactional
    @Query("UPDATE Member m SET m.mPhone = :data WHERE m.mNo = :mNo")
    int changeMypagePhone(@Param("mNo") int mNo,
                          @Param("data") String data);

    @Modifying
    @Transactional
    @Query("UPDATE Member m SET m.brandName = :brandName, m.contactTime = :contactTime, m.introduce = :introduce WHERE m.mNo = :mNo")
    int updateFreelancer(@Param("mNo") int mNo,
                    @Param("brandName") String brandName,
                    @Param("contactTime") String contactTime,
                    @Param("introduce") String introduce);


}
