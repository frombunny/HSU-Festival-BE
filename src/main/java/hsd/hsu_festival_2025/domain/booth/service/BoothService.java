package hsd.hsu_festival_2025.domain.booth.service;

import hsd.hsu_festival_2025.domain.booth.entity.enums.BoothType;
import hsd.hsu_festival_2025.domain.booth.web.dto.GetBoothRes;
import hsd.hsu_festival_2025.domain.booth.web.dto.SaveBoothReq;
import hsd.hsu_festival_2025.domain.booth.web.dto.UpdateBoothReq;

import java.util.List;

public interface BoothService {
    void saveBooth(SaveBoothReq saveBoothReq);
    void updateBooth(Long id, UpdateBoothReq updateBoothReq);
    void deleteBooth(Long id);
    List<GetBoothRes> getBoothList(BoothType boothType);
}
