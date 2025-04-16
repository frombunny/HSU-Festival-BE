package hsd.hsu_festival_2025.global.response.code;

public interface BaseResponseCode {
    String getCode();
    String getMessage();
    int getHttpStatus();
}