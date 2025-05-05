package hsd.hsu_festival_2025.domain.chatMessage.service;

import hsd.hsu_festival_2025.domain.chatMessage.entity.ChatMessage;
import hsd.hsu_festival_2025.domain.chatMessage.entity.ChatOwner;
import hsd.hsu_festival_2025.domain.chatMessage.repository.ChatMessageRepository;
import hsd.hsu_festival_2025.domain.chatMessage.web.dto.SendMessageReq;
import hsd.hsu_festival_2025.domain.chatMessage.web.dto.SendMessageRes;
import hsd.hsu_festival_2025.domain.openai.service.AnswerCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final AnswerCreateService answerCreateService;

    // 새로운 메세지 전송
    @Override
    public SendMessageRes addNewChatMessage(SendMessageReq sendMessageReq) {
        // TODO : 비속어 필터링
        String question = sendMessageReq.getNewChat();

        ChatMessage questionMessage = ChatMessage.toEntity(sendMessageReq);
        // DB 저장 - 질문
        chatMessageRepository.save(questionMessage);

        // AI 응답 생성
        String answer = answerCreateService.chat(sendMessageReq.getChatHistory(), sendMessageReq.getNewChat());

        // DB에 저장 - 응답
        ChatMessage newAnswer = ChatMessage.builder()
                .message(answer)
                .chatOwner(ChatOwner.BUGI)
                .createdAt(LocalDateTime.now())
                .build();
        chatMessageRepository.save(newAnswer);

        // 반환
        return new SendMessageRes(answer, questionMessage.getCreatedAt());
    }
}
