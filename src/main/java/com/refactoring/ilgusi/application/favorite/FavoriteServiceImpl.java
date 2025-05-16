package com.refactoring.ilgusi.application.favorite;


import com.refactoring.ilgusi.domain.favorite.interfaces.FavoriteService;
import com.refactoring.ilgusi.domain.member.interfaces.MemberRepository;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceRepository;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceTradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class FavoriteServiceImpl implements FavoriteService {
    private final MemberRepository memberRepository;
    private final ServiceTradeRepository serviceTradeRepository;
    private final ServiceRepository serviceRepository;

    @Override
    public boolean isFavoriteChecked(int memberNo, int serviceNo) {
        return false;
    }
}
