package com.challang.backend.file.dto;

public record PresignedUrlResponse(
        String presignedUrl,
        String key
) {
}
