package hsd.hsu_festival_2025.domain.community.repository;

import hsd.hsu_festival_2025.domain.community.entity.CommunityMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityChatRepository extends JpaRepository<CommunityMessage, Long> {
    // 최신 100개 -> 초기 로딩
    List<CommunityMessage> findTop100ByOrderByCreatedAtDesc();

    // 페이징 조회 -> 무한 스크롤
    Page<CommunityMessage> findAllByOrderByCreatedAtDesc(Pageable pageable);

}
