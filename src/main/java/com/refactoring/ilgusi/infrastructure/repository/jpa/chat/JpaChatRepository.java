package com.refactoring.ilgusi.infrastructure.repository.jpa.chat;

import com.refactoring.ilgusi.domain.chat.interfaces.ChatRepository;

public class JpaChatRepository implements ChatRepository {
    private final SpringDataChatRepository jpaRepository;

    public JpaChatRepository(SpringDataChatRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

}
