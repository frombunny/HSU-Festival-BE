package hsd.hsu_festival_2025.domain.mock;

import hsd.hsu_festival_2025.global.external.S3Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class MockServiceImpl implements MockService {
    private final S3Service s3Service;
    @Override
    public String uploadImage(String fileName, MultipartFile file)  throws IOException {
        String uploadFileName = s3Service.generateFileName(file);
        return s3Service.uploadFile(uploadFileName, file);
    }
}
