package hsd.hsu_festival_2025.domain.notice.service;

import hsd.hsu_festival_2025.domain.notice.web.dto.AddNoticeReq;
import hsd.hsu_festival_2025.domain.notice.web.dto.NoticeDetailRes;
import hsd.hsu_festival_2025.domain.notice.web.dto.NoticeSummaryRes;

import java.util.List;

public interface NoticeService{
    // 공지사항 추가
    NoticeDetailRes createNotice(AddNoticeReq addNoticeReq);
    // 공지사항 전체 조회
    List<NoticeSummaryRes> getNoticeList();
    // 공지사항 단건 조회
    NoticeDetailRes getNoticeById(Long id);
}
