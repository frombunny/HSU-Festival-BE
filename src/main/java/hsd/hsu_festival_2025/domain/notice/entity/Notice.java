package hsd.hsu_festival_2025.domain.notice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import hsd.hsu_festival_2025.domain.notice.web.dto.AddNoticeReq;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; // 제목

    @Column(columnDefinition = "TEXT")
    private String content; // 내용

    private LocalDate date; // 작성 날짜

    @JsonProperty("isPined")
    private boolean isPined; // 고정 여부

    // Req -> Dto 변환 메서드
    public static Notice toEntity(AddNoticeReq addNoticeReq) {
        return Notice.builder()
                .title(addNoticeReq.getTitle())
                .content(addNoticeReq.getContent())
                .date(addNoticeReq.getDate())
                .isPined(addNoticeReq.isPined())
                .build();
    }
}
