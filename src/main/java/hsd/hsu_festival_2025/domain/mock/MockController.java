package hsd.hsu_festival_2025.domain.mock;

import hsd.hsu_festival_2025.global.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController()
@RequiredArgsConstructor
public class MockController {
    private final MockService mockService;

    @PostMapping("/uploadImage")
    public SuccessResponse<String> uploadImage(@RequestPart("data") String fileName,
                                             @RequestPart("file") MultipartFile file) throws IOException {
        String fileUrl = mockService.uploadImage(fileName, file);
        return SuccessResponse.created(fileUrl);
    }
}

