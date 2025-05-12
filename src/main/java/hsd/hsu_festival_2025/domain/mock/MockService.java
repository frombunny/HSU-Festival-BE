package hsd.hsu_festival_2025.domain.mock;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MockService {
    // Mock 파일 업로드
    String uploadImage(String fileName, MultipartFile file) throws IOException;
}
