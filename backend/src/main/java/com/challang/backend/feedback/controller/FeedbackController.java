package com.challang.backend.feedback.controller;

import com.challang.backend.auth.annotation.CurrentUser;
import com.challang.backend.feedback.dto.FeedbackRequest;
import com.challang.backend.feedback.dto.FeedbackResponse;
import com.challang.backend.feedback.service.FeedbackService;
import com.challang.backend.user.entity.User;
import com.challang.backend.util.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Feedback", description = "피드백 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @Operation(summary = "피드백 등록/수정", description = "특정 술에 대해 피드백을 등록하거나 수정합니다. 이미 등록된 피드백이 있다면 type만 변경됩니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "피드백 등록 또는 수정 성공"),
            @ApiResponse(responseCode = "400", description = "요청 값이 잘못된 경우 (예: 잘못된 enum 값)"),
            @ApiResponse(responseCode = "404", description = "해당 술을 찾을 수 없음")
    })
    @PostMapping
    public ResponseEntity<BaseResponse<FeedbackResponse>> saveFeedback(
            @CurrentUser User user,
            @RequestBody @Valid FeedbackRequest request
    ) {
        FeedbackResponse response = feedbackService.saveFeedback(user, request);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }
}
