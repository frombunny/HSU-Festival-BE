package hsd.hsu_festival_2025.domain.notice.web.controller;

import hsd.hsu_festival_2025.domain.notice.service.NoticeService;
import hsd.hsu_festival_2025.domain.notice.web.dto.AddNoticeReq;
import hsd.hsu_festival_2025.domain.notice.web.dto.NoticeDetailRes;
import hsd.hsu_festival_2025.domain.notice.web.dto.NoticeSummaryRes;
import hsd.hsu_festival_2025.global.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    // 공지사항 작성
    @PostMapping
    public SuccessResponse<?> addNotice(@RequestBody AddNoticeReq addNoticeReq) {
        NoticeDetailRes noticeDetailRes = noticeService.createNotice(addNoticeReq);
        return SuccessResponse.created(noticeDetailRes);
    }

    // 공지사항 목록 조회
    @GetMapping
    public SuccessResponse<?> getAllNotices() {
        List<NoticeSummaryRes> noticeResList = noticeService.getNoticeList();
        return SuccessResponse.from(noticeResList);
    }

    // 공지사항 단건 조회
    @GetMapping("/{notice_id}")
    public SuccessResponse<?> getAllNotices(@PathVariable Long notice_id) {
        NoticeDetailRes noticeDetailRes = noticeService.getNoticeById(notice_id);
        return SuccessResponse.from(noticeDetailRes);
    }

}
