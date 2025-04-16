package hsd.hsu_festival_2025.global.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import hsd.hsu_festival_2025.global.response.code.BaseResponseCode;
import hsd.hsu_festival_2025.global.response.code.GlobalSuccessCode;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonPropertyOrder({"isSuccess", "timestamp", "code", "httpStatus", "message", "data"})
public class SuccessResponse<T> extends BaseResponse{
    private final int httpStatus;
    private final T data;

    @Builder
    public SuccessResponse(T data, BaseResponseCode baseResponseCode) {
        super(true, baseResponseCode.getCode(), baseResponseCode.getMessage());
        this.data = data;
        this.httpStatus = baseResponseCode.getHttpStatus();
    }

    // 200 OK 응답
    public static <T> SuccessResponse<T> of(T data) {
        return new SuccessResponse<>(data, GlobalSuccessCode.SUCCESS_OK);
    }

    public static <T> SuccessResponse<T> of(T data, BaseResponseCode baseResponseCode) {
        return new SuccessResponse<>(data, baseResponseCode);
    }

}
