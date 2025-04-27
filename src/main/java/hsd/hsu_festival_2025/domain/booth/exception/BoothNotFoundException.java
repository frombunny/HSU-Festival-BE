package hsd.hsu_festival_2025.domain.booth.exception;

import hsd.hsu_festival_2025.global.exception.BaseException;

public class BoothNotFoundException extends BaseException {
    public BoothNotFoundException() { super(BoothErrorCode.BOOTH_NOT_FOUND_404); }
}
