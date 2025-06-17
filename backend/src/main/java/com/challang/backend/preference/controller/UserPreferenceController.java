package com.challang.backend.preference.controller;

import com.challang.backend.auth.annotation.CurrentUser;
import com.challang.backend.user.entity.User;
import com.challang.backend.preference.code.UserPreferenceCode;
import com.challang.backend.preference.dto.UserPreferenceRequest;
import com.challang.backend.preference.service.UserPreferenceService;
import com.challang.backend.util.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User Preference", description = "사용자 주류 선호 설정 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user-preference")
public class UserPreferenceController {

    private final UserPreferenceService userPreferenceService;

    @Operation(summary = "선호도 설정", description = "유저의 주류 선호도를 설정합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "설정 성공"),
            @ApiResponse(responseCode = "404", description = "유저 또는 항목 없음")
    })
    @PostMapping
    public ResponseEntity<BaseResponse<Void>> setUserPreference(
            @CurrentUser User user,
            @RequestBody @Valid UserPreferenceRequest request
    ) {
        userPreferenceService.setUserPreference(user.getUserId(), request);
        return ResponseEntity.ok(new BaseResponse<>(UserPreferenceCode.PREFERENCE_SAVE_SUCCESS));
    }
}
