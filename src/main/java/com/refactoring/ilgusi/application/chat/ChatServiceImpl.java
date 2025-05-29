package com.refactoring.ilgusi.application.chat;


import com.refactoring.ilgusi.domain.chat.Chat;
import com.refactoring.ilgusi.domain.chat.ChatContent;
import com.refactoring.ilgusi.domain.chat.interfaces.ChatRepository;
import com.refactoring.ilgusi.domain.chat.interfaces.ChatService;
import com.refactoring.ilgusi.domain.favorite.Favorite;
import com.refactoring.ilgusi.domain.favorite.interfaces.FavoriteQRepository;
import com.refactoring.ilgusi.domain.favorite.interfaces.FavoriteRepository;
import com.refactoring.ilgusi.domain.favorite.interfaces.FavoriteService;
import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.member.interfaces.MemberRepository;
import com.refactoring.ilgusi.domain.service.ServiceItem;
import com.refactoring.ilgusi.domain.service.dto.ServiceInfoDto;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;

    @Override
    public Chat selectOneRoom(int serviceNo, int userNo, int freeNo) {
        return null;
    }

    @Override
    public Chat createChat(int serviceNo, int userNo, int freeNo) {
        return null;
    }

    @Override
    public Chat insertChat(int chatNo, String welcomeMsg) {
        return null;
    }

    @Override
    public List<ChatContent> chatContentList(int chatNo) {
        return List.of();
    }

    @Override
    public void updateReadStatus(int chatNo, int yourNo) {

    }
}
