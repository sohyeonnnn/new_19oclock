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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "favorite_seq_gen")
    @SequenceGenerator(name = "favorite_seq_gen", sequenceName = "FAVORITE_SEQ", allocationSize = 1)
    private int favoriteNo;	//찜하기 번호
    @Column(nullable=false)
    private int memberNo;	//회원 번호
    @Column(nullable=false)
    private int serviceNo;	//서비스 번호
}
