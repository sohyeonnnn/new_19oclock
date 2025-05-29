package com.refactoring.ilgusi.domain.chat.interfaces;


import com.refactoring.ilgusi.domain.chat.Chat;
import com.refactoring.ilgusi.domain.chat.ChatContent;

import java.util.List;

public interface ChatService {
    Chat selectOneRoom(int serviceNo, int userNo, int freeNo);
    Chat createChat(int serviceNo, int userNo, int freeNo);
    Chat insertChat(int chatNo, String welcomeMsg);
    List<ChatContent> chatContentList(int chatNo);
    void updateReadStatus(int chatNo, int yourNo);
}
