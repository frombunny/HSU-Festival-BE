package hsd.hsu_festival_2025.domain.booth.service;

import hsd.hsu_festival_2025.domain.booth.entity.enums.BoothType;
import hsd.hsu_festival_2025.domain.booth.web.dto.GetBoothRes;
import hsd.hsu_festival_2025.domain.booth.web.dto.SaveBoothReq;
import hsd.hsu_festival_2025.domain.booth.web.dto.UpdateBoothReq;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BoothService {
    void saveBooth(SaveBoothReq saveBoothReq, MultipartFile multipartFile) throws IOException;
    void updateBooth(Long id, UpdateBoothReq updateBoothReq, MultipartFile multipartFile) throws IOException;
    void deleteBooth(Long id);
    List<GetBoothRes> getBoothList(BoothType boothType);
}
