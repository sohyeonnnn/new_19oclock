package com.refactoring.ilgusi.infrastructure.repository.jpa.member;

import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.member.RoleEnum;
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
    public Member saveMember(Member m) {
        return jpaRepository.save(m);
    }

    @Override
    public Optional<Member> findBymId(String mid) {
        return jpaRepository.findBymId(mid);
    }

    @Override
    public Optional<Member> findBymNo(Integer mNo) {
        return jpaRepository.findBymNo(mNo);
    }

    @Override
    public Optional<Member> searchIdPw(Member m) {
        return jpaRepository.findBymNameAndmPhoneOrmIdAndmPhone(m.getMName(), m.getMPhone(), m.getMId());
    }

    @Override
    public int changePw(Member m) {
        return jpaRepository.changePw(m.getMNo(), m.getMPw());
    }

    @Override
    public Optional<Member> changeMypage(int mNo, String data, String object) {
        if(object.equals("email")){
            jpaRepository.changeMypageEmail(mNo, data);
        }else if(object.equals("phone")){
            jpaRepository.changeMypagePhone(mNo, data);
        }else if(object.equals("pw")){
            jpaRepository.changePw(mNo, data);
        }
        return jpaRepository.findBymNo(mNo);
    }

    @Override
    public void deleteMember(int mNo) {
        jpaRepository.deleteBymNo(mNo);
    }

    @Override
    public Optional<Member> changeGrade(int mNo, RoleEnum user, RoleEnum free) {
        jpaRepository.changeGrade(mNo, RoleEnum.USER, RoleEnum.FREELANCER);
        return findBymNo(mNo);
    }

    @Override
    public void settingMemberGrade(Member m) {
    }

    @Override
    public Optional<Member> updateFreelancer(Member m) {
        System.out.println(m.toString());
        jpaRepository.updateFreelancer(m.getMNo(), m.getBrandName(), m.getContactTime(), m.getIntroduce());
        return findBymNo(m.getMNo());
    }
}