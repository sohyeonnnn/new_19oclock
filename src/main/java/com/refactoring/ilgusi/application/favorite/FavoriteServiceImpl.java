package com.refactoring.ilgusi.application.favorite;


import com.refactoring.ilgusi.domain.favorite.Favorite;
import com.refactoring.ilgusi.domain.favorite.interfaces.FavoriteRepository;
import com.refactoring.ilgusi.domain.favorite.interfaces.FavoriteService;
import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.member.interfaces.MemberRepository;
import com.refactoring.ilgusi.domain.service.ServiceItem;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceRepository;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceTradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final MemberRepository memberRepository;
    private final ServiceRepository serviceRepository;

    @Override
    public boolean isFavoriteChecked(int memberNo, int serviceNo) {
        return false;
    }

    @Override
    public void insertHeart(int serviceNo, int memberNo) {
   /*     Member member = memberRepository.findByMemberNo(memberNo).orElseThrow();
        ServiceItem service = serviceRepository.findById(serviceNo).orElseThrow();

        Favorite favorite = new Favorite();
        favorite.setMember(member);
        favorite.setService(service);
        favoriteRepository.insertFavorite(favorite);*/
    }

    @Override
    public void deleteHeart(int serviceNo, int memberNo) {
        //favoriteRepository.deleteHeart(serviceNo, memberNo);
    }
}
