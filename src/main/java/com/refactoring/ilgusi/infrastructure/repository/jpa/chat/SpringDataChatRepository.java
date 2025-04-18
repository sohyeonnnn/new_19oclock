package com.refactoring.ilgusi.infrastructure.repository.jpa.chat;

import com.refactoring.ilgusi.domain.chat.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataChatRepository extends JpaRepository<Chat, Integer> {
}
