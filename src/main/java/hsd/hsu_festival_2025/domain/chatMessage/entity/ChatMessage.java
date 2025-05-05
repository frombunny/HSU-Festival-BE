package hsd.hsu_festival_2025.domain.chatMessage.entity;

import hsd.hsu_festival_2025.domain.chatMessage.web.dto.SendMessageReq;
import hsd.hsu_festival_2025.global.entity.BaseEntity;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ChatOwner chatOwner; // 발화 주체
    private LocalDateTime createdAt;

    public static ChatMessage toEntity(SendMessageReq sendMessageReq) {
        return ChatMessage.builder()
                .message(sendMessageReq.getNewChat())
                .chatOwner(ChatOwner.USER)
                .createdAt(LocalDateTime.now())
                .build();
    }
}