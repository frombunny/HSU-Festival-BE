package hsd.hsu_festival_2025.domain.notice.service;

import hsd.hsu_festival_2025.domain.notice.entity.Notice;
import hsd.hsu_festival_2025.domain.notice.exception.NoticeNotFoundException;
import hsd.hsu_festival_2025.domain.notice.repository.NoticeRepository;
import hsd.hsu_festival_2025.domain.notice.web.dto.AddNoticeReq;
import hsd.hsu_festival_2025.domain.notice.web.dto.NoticeDetailRes;
import hsd.hsu_festival_2025.domain.notice.web.dto.NoticeSummaryRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;

    // 공지사항 작성
    @Override
    public NoticeDetailRes createNotice(AddNoticeReq addNoticeReq) {
        Notice notice = Notice.toEntity(addNoticeReq);
        // DB 저장
        noticeRepository.save(notice);
        // 반환
        return new NoticeDetailRes(
                notice.getId(),
                notice.getTitle(),
                notice.getContent(),
                notice.getDate(),
                notice.isPined()
        );
    }

    // 공지사항 전체 조회
    @Override
    public List<NoticeSummaryRes> getNoticeList() {
        return noticeRepository.findAll(Sort.by(Sort.Direction.DESC, "date")).stream()
                // Notice Entity -> NoticeSummaryRes Dto
                .map(notice -> new NoticeSummaryRes(
                        notice.getId(),
                        notice.getTitle(),
                        notice.getDate(),
                        notice.isPined()
                ))
                // stream 결과를 리스트로 수집
                .toList();
    }

    // 공지사항 단건 조회
    @Override
    public NoticeDetailRes getNoticeById(Long id) {
        // DB 조회
        Notice notice = noticeRepository.findById(id)
                // 404: 존재하지 않는 공지사항
                .orElseThrow(NoticeNotFoundException::new);

        // 반환
        return new NoticeDetailRes(
                notice.getId(),
                notice.getTitle(),
                notice.getContent(),
                notice.getDate(),
                notice.isPined()
        );
    }
}
