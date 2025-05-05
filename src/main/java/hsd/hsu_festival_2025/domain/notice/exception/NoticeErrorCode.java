package hsd.hsu_festival_2025.domain.notice.exception;

import hsd.hsu_festival_2025.global.response.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static hsd.hsu_festival_2025.global.constant.StaticValue.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum NoticeErrorCode implements BaseResponseCode {
    NOTICE_NOT_FOUND_404("NOTICE_NOT_FOUND_404", NOT_FOUND, "존재하지 않는 공지사항 입니다.");

    private final String code;
    private final int httpStatus;
    private final String message;
}
