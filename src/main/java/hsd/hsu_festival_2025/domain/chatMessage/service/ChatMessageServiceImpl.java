package hsd.hsu_festival_2025.domain.chatMessage.service;

import hsd.hsu_festival_2025.domain.chatMessage.entity.ChatMessage;
import hsd.hsu_festival_2025.domain.chatMessage.entity.ChatOwner;
import hsd.hsu_festival_2025.domain.chatMessage.repository.ChatMessageRepository;
import hsd.hsu_festival_2025.domain.chatMessage.web.dto.SendMessageReq;
import hsd.hsu_festival_2025.domain.chatMessage.web.dto.SendMessageRes;
import hsd.hsu_festival_2025.domain.community.exception.BadWordException;
import hsd.hsu_festival_2025.domain.openai.service.AnswerCreateService;
import hsd.hsu_festival_2025.global.validator.BadWordValidator;
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
    private final BadWordValidator badWordValidator;

    // 새로운 메세지 전송
    @Override
    public SendMessageRes addNewChatMessage(SendMessageReq sendMessageReq) {
        ChatMessage questionMessage = ChatMessage.toEntity(sendMessageReq);
        // DB 저장 - 질문
        chatMessageRepository.save(questionMessage);

        // 비속어 필터링
        if(badWordValidator.containsBadWord(sendMessageReq.getNewChat())){
            throw new BadWordException(); // 403 : 비속어 감지됨 - 요청 거부
        }

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
