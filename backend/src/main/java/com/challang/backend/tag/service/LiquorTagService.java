package com.challang.backend.tag.service;

import com.challang.backend.global.exception.BaseException;
import com.challang.backend.liquor.code.LiquorCode;
import com.challang.backend.liquor.entity.Liquor;
import com.challang.backend.liquor.repository.LiquorRepository;
import com.challang.backend.tag.code.TagCode;
import com.challang.backend.tag.dto.request.LiquorTagRequest;
import com.challang.backend.tag.dto.response.LiquorTagResponse;
import com.challang.backend.tag.entity.LiquorTag;
import com.challang.backend.tag.entity.Tag;
import com.challang.backend.tag.repository.LiquorTagRepository;
import com.challang.backend.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.challang.backend.tag.constant.TagConstant.*;

@Service
@RequiredArgsConstructor
@Transactional
public class LiquorTagService {

    private final LiquorRepository liquorRepository;
    private final TagRepository tagRepository;
    private final LiquorTagRepository liquorTagRepository;

    public LiquorTagResponse create(Long liquorId, LiquorTagRequest request) {
        Liquor liquor = liquorRepository.findById(liquorId)
                .orElseThrow(() -> new BaseException(LiquorCode.LIQUOR_NOT_FOUND));

        Tag tag = tagRepository.findById(request.id())
                .orElseThrow(() -> new BaseException(TagCode.TAG_NOT_FOUND));

        validateCoreTagCountOnAdd(liquorId, request.isCore());

        LiquorTag liquorTag = LiquorTag.builder()
                .liquor(liquor)
                .tag(tag)
                .isCore(request.isCore())
                .build();

        return LiquorTagResponse.fromEntity(liquorTagRepository.save(liquorTag));
    }

    @Transactional(readOnly = true)
    public List<LiquorTagResponse> findByLiquorId(Long liquorId) {
        if (!liquorRepository.existsById(liquorId)) {
            throw new BaseException(LiquorCode.LIQUOR_NOT_FOUND);
        }

        List<LiquorTag> liquorTags = liquorTagRepository.findByLiquorId(liquorId);
        return liquorTags.stream()
                .map(LiquorTagResponse::fromEntity)
                .toList();
    }

    public void delete(Long liquorId, Long liquorTagId) {
        LiquorTag liquorTag = liquorTagRepository.findById(liquorTagId)
                .orElseThrow(() -> new BaseException(TagCode.LIQUOR_TAG_NOT_FOUND));

        if (!liquorTag.getLiquor().getId().equals(liquorId)) {
            throw new BaseException(TagCode.LIQUOR_TAG_MISMATCH);
        }

        validateCoreTagCountOnDelete(liquorId, liquorTag);

        liquorTagRepository.delete(liquorTag);
    }

    private void validateCoreTagCountOnAdd(Long liquorId, boolean isCore) {
        if (!isCore) {
            return;
        }

        long coreTagCount = liquorTagRepository.countByLiquorIdAndIsCoreTrue(liquorId);
        if (coreTagCount >= MAX_CORE_TAG) {
            throw new BaseException(TagCode.INVALID_CORE_TAG_COUNT);
        }
    }

    private void validateCoreTagCountOnDelete(Long liquorId, LiquorTag liquorTag) {
        if (!liquorTag.isCore()) {
            return;
        }

        long coreTagCount = liquorTagRepository.countByLiquorIdAndIsCoreTrue(liquorId);
        if (coreTagCount <= MIN_CORE_TAG) {
            throw new BaseException(TagCode.INVALID_CORE_TAG_COUNT);
        }
    }
}
