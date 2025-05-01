package com.refactoring.ilgusi.domain.favorite;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fNo;	//찜하기 번호
    @Column(nullable=false)
    private int mNo;	//회원 번호
    @Column(nullable=false)
    private int sNo;	//서비스 번호
}
