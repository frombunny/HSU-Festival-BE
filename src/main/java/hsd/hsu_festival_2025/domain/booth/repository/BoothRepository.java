package hsd.hsu_festival_2025.domain.booth.repository;

import hsd.hsu_festival_2025.domain.booth.entity.Booth;
import hsd.hsu_festival_2025.domain.booth.entity.enums.BoothType;
import hsd.hsu_festival_2025.domain.booth.exception.BoothNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoothRepository extends JpaRepository<Booth, Long> {
    default Booth getBoothById(Long id){
        return findById(id).orElseThrow(BoothNotFoundException::new);
    }

    List<Booth> findAllByType(BoothType boothType);
}
