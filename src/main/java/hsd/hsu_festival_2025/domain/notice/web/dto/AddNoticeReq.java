package hsd.hsu_festival_2025.domain.notice.web.dto;

import lombok.Getter;

import java.time.LocalDate;

// Req는 record 보다 일반 클래스 권장
@Getter
public class AddNoticeReq {
    private String title;
    private String content;
    private LocalDate date;
    private boolean isPined;
}