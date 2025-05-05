package hsd.hsu_festival_2025.domain.notice.repository;

import hsd.hsu_festival_2025.domain.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
