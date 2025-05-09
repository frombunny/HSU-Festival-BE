package hsd.hsu_festival_2025.domain.booth.service;

import hsd.hsu_festival_2025.domain.booth.entity.Booth;
import hsd.hsu_festival_2025.domain.booth.entity.enums.BoothType;
import hsd.hsu_festival_2025.domain.booth.repository.BoothRepository;
import hsd.hsu_festival_2025.domain.booth.web.dto.GetBoothRes;
import hsd.hsu_festival_2025.domain.booth.web.dto.SaveBoothReq;
import hsd.hsu_festival_2025.domain.booth.web.dto.UpdateBoothReq;
import hsd.hsu_festival_2025.global.external.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoothServiceImpl implements BoothService {

    private final BoothRepository boothRepository;
    private final S3Service s3Service;

    @Override
    @Transactional
    public void saveBooth(SaveBoothReq saveBoothReq, MultipartFile file) throws IOException {
        String fileName = s3Service.generateFileName(file);
        String fileUrl = s3Service.uploadFile(fileName, file);

        Booth booth = Booth.toEntity(saveBoothReq,fileUrl);
        boothRepository.save(booth);
    }

    @Override
    @Transactional
    public void updateBooth(Long id, UpdateBoothReq updateBoothReq, MultipartFile file) throws IOException {
        String fileName = s3Service.generateFileName(file);
        String fileUrl = s3Service.uploadFile(fileName, file);

        Booth booth = boothRepository.getBoothById(id);
        booth.updateBooth(updateBoothReq, fileUrl);
    }

    @Override
    @Transactional
    public void deleteBooth(Long id) {
        Booth booth = boothRepository.getBoothById(id);
        boothRepository.delete(booth);
    }

    @Override
    public List<GetBoothRes> getBoothList(BoothType boothType) {
        List<Booth> boothList = boothRepository.findAllByType(boothType);

        return boothList.stream()
                .map(booth -> new GetBoothRes(
                        booth.getId(),
                        booth.getName(),
                        booth.getTime(),
                        booth.getDescription(),
                        booth.getImageUrl()
                ))
                .toList();
    }
}
