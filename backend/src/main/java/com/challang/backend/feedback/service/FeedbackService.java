package com.challang.backend.feedback.service;


import com.challang.backend.feedback.dto.*;
import com.challang.backend.feedback.entity.*;
import com.challang.backend.feedback.repository.LiquorFeedbackRepository;
import com.challang.backend.global.exception.BaseException;
import com.challang.backend.liquor.code.LiquorCode;
import com.challang.backend.liquor.entity.Liquor;
import com.challang.backend.liquor.repository.LiquorRepository;
import com.challang.backend.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final LiquorRepository liquorRepository;
    private final LiquorFeedbackRepository feedbackRepository;


    public FeedbackResponse saveFeedback(User user, FeedbackRequest request) {
        Liquor liquor = liquorRepository.findById(request.liquorId())
                .orElseThrow(() -> new BaseException(LiquorCode.LIQUOR_NOT_FOUND));

        FeedbackType type = request.type();

        feedbackRepository.findByUserAndLiquor(user, liquor)
                .ifPresentOrElse(
                        feedback -> feedback.updateType(type),
                        () -> feedbackRepository.save(
                                LiquorFeedback.builder()
                                        .user(user)
                                        .liquor(liquor)
                                        .type(type)
                                        .build()
                        )
                );

        return new FeedbackResponse(liquor.getId(), type);

    }

}
