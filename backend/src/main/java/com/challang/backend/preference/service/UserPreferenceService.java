package com.challang.backend.preference.service;

import com.challang.backend.global.exception.BaseException;
import com.challang.backend.liquor.code.LiquorCode;
import com.challang.backend.liquor.entity.*;
import com.challang.backend.liquor.repository.*;
import com.challang.backend.preference.entity.LiquorPreferenceLevel;
import com.challang.backend.preference.entity.LiquorPreferenceTag;
import com.challang.backend.preference.entity.LiquorPreferenceType;
import com.challang.backend.preference.repository.LiquorPreferenceLevelRepository;
import com.challang.backend.preference.repository.LiquorPreferenceTagRepository;
import com.challang.backend.preference.repository.LiquorPreferenceTypeRepository;
import com.challang.backend.tag.code.TagCode;
import com.challang.backend.tag.entity.Tag;
import com.challang.backend.tag.repository.TagRepository;
import com.challang.backend.user.entity.User;
import com.challang.backend.user.exception.UserErrorCode;
import com.challang.backend.preference.dto.UserPreferenceRequest;
import com.challang.backend.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserPreferenceService {

    private final UserRepository userRepository;

    private final LiquorLevelRepository liquorLevelRepository;
    private final LiquorTypeRepository liquorTypeRepository;
    private final TagRepository tagRepository;

    private final LiquorPreferenceLevelRepository preferenceLevelRepository;
    private final LiquorPreferenceTagRepository preferenceTagRepository;
    private final LiquorPreferenceTypeRepository preferenceTypeRepository;


    public void setUserPreference(Long userId, UserPreferenceRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BaseException(UserErrorCode.USER_NOT_FOUND));

        LiquorLevel level = liquorLevelRepository.findById(request.levelId())
                .orElseThrow(() -> new BaseException(LiquorCode.LIQUOR_LEVEL_NOT_FOUND));

        List<LiquorType> types = new ArrayList<>();
        for (Long typeId : request.typeIds()) {
            LiquorType type = liquorTypeRepository.findById(typeId)
                    .orElseThrow(() -> new BaseException(LiquorCode.LIQUOR_TYPE_NOT_FOUND));
            types.add(type);
        }

        List<Tag> tags = new ArrayList<>();
        for (Long tagId : request.tagIds()) {
            Tag tag = tagRepository.findById(tagId)
                    .orElseThrow(() -> new BaseException(TagCode.TAG_NOT_FOUND));
            tags.add(tag);
        }

        // 선호 level 저장
        preferenceLevelRepository.save(
                LiquorPreferenceLevel.builder()
                        .user(user)
                        .liquorLevel(level)
                        .build()
        );

        // 선호 types 저장
        for (LiquorType type : types) {
            boolean exists = preferenceTypeRepository.existsByUserAndLiquorType(user, type);
            if (!exists) {
                preferenceTypeRepository.save(
                        LiquorPreferenceType.builder()
                                .user(user)
                                .liquorType(type)
                                .build()
                );
            }
        }

        // 선호 tags 저장
        for (Tag tag : tags) {
            boolean exists = preferenceTagRepository.existsByUserAndTag(user, tag);
            if (!exists) {
                preferenceTagRepository.save(
                        LiquorPreferenceTag.builder()
                                .user(user)
                                .tag(tag)
                                .build()
                );
            }
        }


    }
}
