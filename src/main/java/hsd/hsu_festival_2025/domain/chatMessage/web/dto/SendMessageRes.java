package hsd.hsu_festival_2025.domain.chatMessage.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record SendMessageRes(
        String content,
        @JsonFormat(pattern = "HH:mm")
        LocalDateTime date // 응답 생성 시각
) {
}

