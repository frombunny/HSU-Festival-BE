package hsd.hsu_festival_2025.domain.community.exception;

import hsd.hsu_festival_2025.global.response.code.BaseResponseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static hsd.hsu_festival_2025.global.constant.StaticValue.FORBIDDEN;

@Getter
@RequiredArgsConstructor
public enum CommunityErrorCode implements BaseResponseCode {
    BAD_WORD_403("BAD_WORD_403", FORBIDDEN, "작성된 내용을 다시 한번 확인해주세요.");

    private final String code;
    private final int httpStatus;
    private final String message;
}
