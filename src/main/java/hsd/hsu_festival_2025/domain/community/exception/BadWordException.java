package hsd.hsu_festival_2025.domain.community.exception;

import hsd.hsu_festival_2025.global.exception.BaseException;

public class BadWordException extends BaseException {
    public BadWordException() { super(CommunityErrorCode.BAD_WORD_403);}
}
