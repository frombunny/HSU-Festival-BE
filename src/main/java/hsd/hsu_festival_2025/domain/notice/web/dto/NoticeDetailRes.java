package hsd.hsu_festival_2025.domain.notice.web.dto;

import java.time.LocalDate;

// Res는 record 권장
// 공지사항 작성, 단건 조회 Response
public record NoticeDetailRes(
        Long id,
        String title,
        String content,
        LocalDate date,
        boolean isPined
){}