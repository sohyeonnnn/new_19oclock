package com.refactoring.ilgusi.infrastructure.repository.jpa.member;

import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.member.RoleEnum;
import com.refactoring.ilgusi.domain.member.interfaces.MemberRepository;

import java.util.Optional;

// 🔸 공통 인터페이스를 구현하고 내부에서 Spring Data JPA를 사용

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
    public Optional<Member> searchIdPw(Member m) {
        return jpaRepository.findBymNameAndmPhoneOrmIdAndmPhone(m.getMName(), m.getMPhone(), m.getMId());
    }

    @Override
    public int changePw(Member m) {
        return jpaRepository.changePw(m.getMPw(), m.getMId());
    }

    @Override
    public void changeMypage(String mId, String data, String object) {
    }

    @Override
    public void deleteMember(String mId) {
    }

    @Override
    public Optional<Member> changeGrade(String mId, RoleEnum user, RoleEnum free) {
        jpaRepository.changeGrade(mId, RoleEnum.USER, RoleEnum.FREELANCER);
        return findBymId(mId);
    }

    @Override
    public void settingMemberGrade(Member m) {
    }

    @Override
    public void tradeStatus(int mNo) {
    }

    @Override
    public void setDeleteStatusY(String mId) {
    }
}