package com.challang.backend.util.aws.service;


import java.net.URL;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Presigner s3Presigner;
    private final S3Client s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    private static final String BASE_PREFIX = "images";

    // S3에 업로드하기 위한 Presigned URL을 생성
    public URL generatePresignedUrl(String key) {
        String contentType = detectContentType(key);

        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(contentType)
                .build();

        PresignedPutObjectRequest presignedRequest =
                s3Presigner.presignPutObject(builder ->
                        builder.putObjectRequest(objectRequest)
                                .signatureDuration(Duration.ofMinutes(10)));

        return presignedRequest.url();
    }

    // 파일 삭제
    public void deleteByKey(String key) {
        DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        s3Client.deleteObject(deleteRequest);
    }


    // S3 저장 경로 생성
    public String buildS3Key(String domain, String filename) {
        return BASE_PREFIX + "/" + domain + "/" + filename;
    }

    // content-type 추출
    private String detectContentType(String filename) {
        String extension = "";
        int idx = filename.lastIndexOf('.');
        if (idx != -1) {
            extension = filename.substring(idx + 1).toLowerCase();
        }

        return switch (extension) {
            case "jpg", "jpeg" -> "image/jpeg";
            case "png" -> "image/png";
            case "webp" -> "image/webp";
            case "gif" -> "image/gif";
            case "heic" -> "image/heic";
            case "svg" -> "image/svg+xml";
            default -> "application/octet-stream"; // 기본값 (불명확할 때)
        };
    }

}
