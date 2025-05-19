package com.refactoring.ilgusi.infrastructure.repository.jpa.member;

import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.member.RoleEnum;
import com.refactoring.ilgusi.domain.member.dto.FreelancerIntroduceDto;
import com.refactoring.ilgusi.domain.member.interfaces.MemberRepository;

import java.util.Optional;

// 공통 인터페이스를 구현하고 내부에서 Spring Data JPA를 사용

//@Repository
public class JpaMemberRepository implements MemberRepository {

    private final SpringDataMemberRepository jpaRepository;

    public JpaMemberRepository(SpringDataMemberRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Member saveMember(Member member) {
        return jpaRepository.save(member);
    }

    @Override
    public Optional<Member> findByMemberId(String memberId) {
        return jpaRepository.findByMemberId(memberId);
    }

    @Override
    public Optional<Member> findByMemberNo(Integer memberNo) {
        return jpaRepository.findByMemberNo(memberNo);
    }

    @Override
    public Optional<Member> searchIdPw(Member m) {
        return jpaRepository.findByMemberNameAndPhoneNoOrMemberIdAndPhoneNo(m.getMemberName(), m.getPhoneNo(), m.getMemberId());
    }

    @Override
    public int changePw(Member member) {
        return jpaRepository.changePw(member.getMemberNo(), member.getMemberPw());
    }

    @Override
    public Optional<Member> changeMypage(int memberNo, String data, String object) {
        if(object.equals("email")){
            jpaRepository.changeMypageEmail(memberNo, data);
        }else if(object.equals("phone")){
            jpaRepository.changeMypagePhone(memberNo, data);
        }else if(object.equals("pw")){
            jpaRepository.changePw(memberNo, data);
        }
        return jpaRepository.findByMemberNo(memberNo);
    }

    @Override
    public void deleteMember(int memberNo) {
        jpaRepository.deleteByMemberNo(memberNo);
    }

    @Override
    public Optional<Member> changeGrade(int memberNo, RoleEnum user, RoleEnum free) {
        jpaRepository.changeGrade(memberNo, RoleEnum.USER, RoleEnum.FREELANCER);
        return findByMemberNo(memberNo);
    }

    @Override
    public Optional<Member> updateFreelancer(Member member) {
        jpaRepository.updateFreelancer(member.getMemberNo(), member.getBrandName(), member.getContactTime(), member.getIntroduce());
        return findByMemberNo(member.getMemberNo());
    }

    @Override
    public Optional<Member> selectFreelancerIntroduce(int memberNo) {
        return jpaRepository.selectFreelancerIntroduce(memberNo);
    }
}