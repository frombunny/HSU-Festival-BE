package hsd.hsu_festival_2025.global.external;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {
    private final S3Client s3Client;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${spring.cloud.aws.region.static}")
    private String region;

    public String uploadFile(String fileName, MultipartFile file) throws IOException {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(fileName) // 버킷 내에서 저장할 파일 이름
                .contentType(file.getContentType())
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

        // URL 생성해서 리턴
        return "https://" + bucket + ".s3." + region + ".amazonaws.com/" + fileName;

    }

    public void deleteFile(String fileUrl) {
        String fileName = extractKeyFromUrl(fileUrl);
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucket)
                .key(fileName)
                .build();

        s3Client.deleteObject(deleteObjectRequest);
    }

    public long getSize(String fileUrl) {
        String fileName = extractKeyFromUrl(fileUrl);
        HeadObjectRequest headBucketRequest = HeadObjectRequest.builder()
                .bucket(bucket)
                .key(fileName)
                .build();

        HeadObjectResponse headObjectResponse = s3Client.headObject(headBucketRequest);
        return headObjectResponse.contentLength();
    }

    public String extractKeyFromUrl(String fileUrl) {
        int index = fileUrl.indexOf(".amazonaws.com/") + ".amazonaws.com/".length();
        return fileUrl.substring(index);
    }

    public String generateFileName(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        String today = LocalDate.now().toString();

        return "form/" + today + "/" + uuid + extension;
    }


}
