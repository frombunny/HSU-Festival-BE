package hsd.hsu_festival_2025.domain.notice.web.dto;

import java.time.LocalDate;

// Res는 record 권장
// 공지사항 목록 조회 Response
public record NoticeSummaryRes(
        Long id,
        String title,
        LocalDate date,
        boolean isPined
) {
}