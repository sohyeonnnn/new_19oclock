package com.refactoring.ilgusi.application.favorite;


import com.refactoring.ilgusi.domain.favorite.Favorite;
import com.refactoring.ilgusi.domain.favorite.interfaces.FavoriteRepository;
import com.refactoring.ilgusi.domain.favorite.interfaces.FavoriteService;
import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.member.interfaces.MemberRepository;
import com.refactoring.ilgusi.domain.service.ServiceItem;
import com.refactoring.ilgusi.domain.service.dto.ServiceInfoDto;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceRepository;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceTradeRepository;
import com.refactoring.ilgusi.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final MemberRepository memberRepository;
    private final ServiceRepository serviceRepository;

    @Override
    public boolean isFavoriteChecked(int memberNo, int serviceNo) {
        return Optional.ofNullable(favoriteRepository.searchFavorite(memberNo, serviceNo))
                .isPresent();
    }

    @Override
    public void insertHeart(int serviceNo, int memberNo) {
        Member member = memberRepository.findByMemberNo(memberNo).orElseThrow();
        ServiceItem service = serviceRepository.findById(serviceNo).orElseThrow();

        Favorite favorite = new Favorite();
        favorite.setMember(member);
        favorite.setService(service);
        favoriteRepository.insertFavorite(favorite);
    }

    @Override
    public void deleteHeart(int serviceNo, int memberNo) {
        Member member = memberRepository.findByMemberNo(memberNo).orElseThrow();
        ServiceItem service = serviceRepository.findById(serviceNo).orElseThrow();

        Favorite favorite = new Favorite();
        favorite.setMember(member);
        favorite.setService(service);
        favoriteRepository.deleteHeart(favorite);
    }

    @Override
    public List<ServiceInfoDto> selectHeartList(int memberNo, String order) {
        return favoriteRepository.selectHeartList(memberNo, order);
    }
}
