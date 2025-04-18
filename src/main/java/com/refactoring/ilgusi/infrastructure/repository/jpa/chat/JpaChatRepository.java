package com.refactoring.ilgusi.infrastructure.repository.jpa.chat;

import com.refactoring.ilgusi.domain.chat.Chat;
import com.refactoring.ilgusi.domain.chat.ChatContent;
import com.refactoring.ilgusi.domain.chat.ChatRepository;
import com.refactoring.ilgusi.domain.favorite.Favorite;
import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.service.Service;
import com.refactoring.ilgusi.domain.service.ServiceInfo;
import com.refactoring.ilgusi.domain.service.ServiceTrade;

import java.util.ArrayList;
import java.util.HashMap;

public class JpaChatRepository implements ChatRepository {
    private final SpringDataChatRepository jpaRepository;

    public JpaChatRepository(SpringDataChatRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

}
