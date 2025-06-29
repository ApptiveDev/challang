package com.challang.backend.file.controller;

import com.challang.backend.file.dto.PresignedUrlResponse;
import com.challang.backend.file.service.FileService;
import com.challang.backend.util.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "File", description = "S3 Presigned URL 발급 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/files")
public class FileController {

    private final FileService fileService;

    @Operation(summary = "Presigned URL 발급", description = "S3에 직접 업로드할 수 있는 URL과 저장할 Key를 반환합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Presigned URL 발급 성공"),
            @ApiResponse(responseCode = "400", description = "요청 파라미터 오류")
    })
    @GetMapping("/upload-url")
    public ResponseEntity<BaseResponse<PresignedUrlResponse>> getUploadUrl(
            @RequestParam String domain,
            @RequestParam String filename
    ) {
        PresignedUrlResponse response = fileService.getPresignedUploadUrl(domain, filename);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }
}
