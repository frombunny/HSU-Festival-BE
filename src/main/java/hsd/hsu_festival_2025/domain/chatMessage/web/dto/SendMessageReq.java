package hsd.hsu_festival_2025.domain.chatMessage.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class SendMessageReq {

    private String newChat; // 새로운 질문 내용

    @JsonProperty("chatHistory")
    private List<ChatHistory> chatHistory; // 이전 채팅 기록

    @Getter
    public static class ChatHistory {
        private int type;         // 0: 사용자, 1: 부기
        private String content;   // 메시지 내용
    }
}
