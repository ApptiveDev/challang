package com.challang.backend.file.service;


import com.challang.backend.file.dto.PresignedUrlResponse;
import com.challang.backend.util.aws.service.S3Service;
import java.net.URL;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileService {

    private final S3Service s3Service;

    public PresignedUrlResponse getPresignedUploadUrl(String domain, String originalFilename) {
        String uniqueFilename = UUID.randomUUID() + "_" + originalFilename;
        String key = s3Service.buildS3Key(domain, uniqueFilename);
        URL url = s3Service.generatePresignedUrl(key);
        return new PresignedUrlResponse(url.toString(), key);
    }


}
