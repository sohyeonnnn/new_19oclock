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
    Optional<Member> findByMemberId(String memberId);
    Optional<Member> findByMemberNo(int memberNo);

    void deleteByMemberNo(int memberNo);

    @Query("SELECT m FROM Member m WHERE (m.memberName = :memberName AND m.phoneNo = :phoneNo) OR (m.memberId = :memberId AND m.phoneNo = :phoneNo)")
    Optional<Member> findByMemberNameAndPhoneNoOrMemberIdAndPhoneNo(@Param("memberName") String memberName,
                                                    @Param("phoneNo") String phoneNo,
                                                    @Param("memberId") String memberId);

    @Modifying
    @Transactional
    @Query("UPDATE Member m SET m.memberPw = :memberPw WHERE m.memberNo = :memberNo")
    int changePw(@Param("memberNo") int memberNo, @Param("memberPw") String memberPw);

    @Modifying
    @Transactional
    @Query("UPDATE Member m SET m.memberGrade = CASE WHEN m.memberGrade = :user THEN :freelancer ELSE :user END  WHERE m.memberNo = :memberNo")
    int changeGrade(@Param("memberNo") int memberNo,
                    @Param("user") RoleEnum user,
                    @Param("freelancer") RoleEnum freelancer);

    @Modifying
    @Transactional
    @Query("UPDATE Member m SET m.email = :data WHERE m.memberNo = :memberNo")
    int changeMypageEmail(@Param("memberNo") int memberNo,
                     @Param("data") String data);

    @Modifying
    @Transactional
    @Query("UPDATE Member m SET m.phoneNo = :data WHERE m.memberNo = :memberNo")
    int changeMypagePhone(@Param("memberNo") int memberNo,
                          @Param("data") String data);

    @Modifying
    @Transactional
    @Query("UPDATE Member m SET m.brandName = :brandName, m.contactTime = :contactTime, m.introduce = :introduce WHERE m.memberNo = :memberNo")
    int updateFreelancer(@Param("memberNo") int memberNo,
                    @Param("brandName") String brandName,
                    @Param("contactTime") String contactTime,
                    @Param("introduce") String introduce);


}
