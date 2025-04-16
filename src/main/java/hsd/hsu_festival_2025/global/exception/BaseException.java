package hsd.hsu_festival_2025.global.exception;

import hsd.hsu_festival_2025.global.response.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {
    private final BaseResponseCode errorCode;
}
