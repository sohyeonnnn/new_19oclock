package com.refactoring.ilgusi.domain.favorite;

import com.refactoring.ilgusi.domain.chat.Chat;
import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.service.ServiceItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "favorite_seq_gen")
    @SequenceGenerator(name = "favorite_seq_gen", sequenceName = "FAVORITE_SEQ", allocationSize = 1)
    private int favoriteNo;	//찜하기 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    private Member member;	//회원 번호
    public void setMember(Member member) {
        this.member = member;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_no")
    private ServiceItem service;	//서비스 번호
    public void setService(ServiceItem service) {
        this.service = service;
    }



}
