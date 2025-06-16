package com.challang.backend.archive.controller;

import com.challang.backend.archive.code.ArchiveCode;
import com.challang.backend.archive.dto.response.ArchiveListResponse;
import com.challang.backend.archive.service.ArchiveService;
import com.challang.backend.auth.annotation.CurrentUser;
import com.challang.backend.user.entity.User;
import com.challang.backend.util.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Archive", description = "아카이브 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/archive")
public class ArchiveController {

    private final ArchiveService archiveService;

    @Operation(summary = "아카이브 목록 조회", description = "로그인 유저의 아카이브한 술들을 커서 기반으로 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공")
    })
    @GetMapping
    public ResponseEntity<BaseResponse<ArchiveListResponse>> getArchivedByCursor(
            @CurrentUser User user,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long cursor,
            @RequestParam(defaultValue = "10") int size
    ) {
        ArchiveListResponse response = archiveService.findAll(user.getUserId(), cursor, keyword, size);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @Operation(summary = "술 아카이브 등록", description = "특정 술을 아카이브에 등록합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "등록 성공"),
            @ApiResponse(responseCode = "400", description = "이미 등록된 경우"),
            @ApiResponse(responseCode = "404", description = "술 또는 유저를 찾을 수 없음")
    })
    @PostMapping("/{liquorId}")
    public ResponseEntity<BaseResponse<Void>> archiveLiquor(
            @CurrentUser User user,
            @PathVariable Long liquorId
    ) {
        archiveService.archiveLiquor(user.getUserId(), liquorId);
        return ResponseEntity.ok(new BaseResponse<>(ArchiveCode.ARCHIVE_SUCCESS));
    }

    @Operation(summary = "술 아카이브 해제", description = "특정 술을 아카이브에서 제거합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "해제 성공"),
            @ApiResponse(responseCode = "404", description = "등록되지 않은 아카이브")
    })
    @DeleteMapping("/{liquorId}")
    public ResponseEntity<BaseResponse<Void>> unarchiveLiquor(
            @CurrentUser User user,
            @PathVariable Long liquorId
    ) {
        archiveService.unarchiveLiquor(user.getUserId(), liquorId);
        return ResponseEntity.ok(new BaseResponse<>(ArchiveCode.UNARCHIVE_SUCCESS));
    }
}