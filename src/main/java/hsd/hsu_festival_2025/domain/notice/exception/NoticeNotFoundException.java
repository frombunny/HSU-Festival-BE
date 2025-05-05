package hsd.hsu_festival_2025.domain.notice.exception;

import hsd.hsu_festival_2025.global.exception.BaseException;

public class NoticeNotFoundException extends BaseException {
    public NoticeNotFoundException() {
        super(NoticeErrorCode.NOTICE_NOT_FOUND_404);
    }
}
