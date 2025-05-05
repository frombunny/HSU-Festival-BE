package hsd.hsu_festival_2025.domain.chatMessage.service;

import hsd.hsu_festival_2025.domain.chatMessage.web.dto.SendMessageReq;
import hsd.hsu_festival_2025.domain.chatMessage.web.dto.SendMessageRes;

public interface ChatMessageService {
    // 새로운 메세지 전송
    SendMessageRes addNewChatMessage(SendMessageReq sendMessageReq);
}
