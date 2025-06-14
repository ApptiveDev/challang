package com.challang.backend.liquor.service;

import com.challang.backend.global.exception.BaseException;
import com.challang.backend.liquor.code.LiquorCode;
import com.challang.backend.liquor.dto.request.LiquorCreateRequest;
import com.challang.backend.liquor.dto.request.LiquorUpdateRequest;
import com.challang.backend.liquor.dto.response.LiquorListResponse;
import com.challang.backend.liquor.dto.response.LiquorResponse;
import com.challang.backend.liquor.entity.Liquor;
import com.challang.backend.liquor.entity.LiquorLevel;
import com.challang.backend.liquor.entity.LiquorType;
import com.challang.backend.liquor.repository.LiquorLevelRepository;
import com.challang.backend.liquor.repository.LiquorRepository;
import com.challang.backend.liquor.repository.LiquorTypeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LiquorService {

    private final LiquorRepository liquorRepository;
    private final LiquorLevelRepository levelRepository;
    private final LiquorTypeRepository typeRepository;

    // TODO: 태그 추가해야함

    // 주류 추가
    public LiquorResponse create(LiquorCreateRequest request) {
        if (liquorRepository.existsByName(request.name())) {
            throw new BaseException(LiquorCode.LIQUOR_ALREADY_EXISTS);
        }

        LiquorLevel level = levelRepository.findById(request.levelId())
                .orElseThrow(() -> new BaseException(LiquorCode.LIQUOR_LEVEL_NOT_FOUND));
        LiquorType type = typeRepository.findById(request.typeId())
                .orElseThrow(() -> new BaseException(LiquorCode.LIQUOR_TYPE_NOT_FOUND));

        Liquor liquor = Liquor.builder()
                .name(request.name())
                .base(request.base())
                .origin(request.origin())
                .color(request.color())
                .minAbv(request.minAbv())
                .maxAbv(request.maxAbv())
                .level(level)
                .type(type)
                .build();

        Liquor saved = liquorRepository.save(liquor);
        return LiquorResponse.fromEntity(saved);
    }

    // 주류 단건 조회
    @Transactional(readOnly = true)
    public LiquorResponse findById(Long id) {
        return LiquorResponse.fromEntity(getLiquorById(id));
    }

    // 주류 전체 조회
    @Transactional(readOnly = true)
    public LiquorListResponse findAll(String cursorName, Integer pageSize) {
        Pageable pageable = PageRequest.of(0, pageSize + 1);

        List<Liquor> liquors = cursorName == null ? liquorRepository.findAllByOrderByNameDesc(pageable)
                : liquorRepository.findByNameLessThanOrderByNameDesc(cursorName, pageable);

        boolean hasNext = liquors.size() > pageSize;
        if (hasNext) {
            liquors = liquors.subList(0, pageSize);
        }

        String nextCursor = hasNext ? liquors.get(pageSize - 1).getName() : null;

        return new LiquorListResponse(
                liquors.stream().map(LiquorResponse::fromEntity).toList(),
                nextCursor,
                hasNext
        );
    }


    // 주류 수정
    public LiquorResponse update(Long id, LiquorUpdateRequest request) {
        Liquor liquor = getLiquorById(id);

        if (request.name() != null && liquorRepository.existsByNameAndIdNot(request.name(), id)) {
            throw new BaseException(LiquorCode.LIQUOR_ALREADY_EXISTS);
        }

        LiquorLevel level = findLevelOrNull(request.levelId());
        LiquorType type = findTypeOrNull(request.typeId());

        liquor.update(request, level, type);
        return LiquorResponse.fromEntity(liquor);
    }

    public void delete(Long id) {
        Liquor liquor = getLiquorById(id);
        liquorRepository.delete(liquor);
    }



    private Liquor getLiquorById(Long id) {
        return liquorRepository.findById(id)
                .orElseThrow(() -> new BaseException(LiquorCode.LIQUOR_NOT_FOUND));
    }

    private LiquorLevel findLevelOrNull(Long levelId) {
        if (levelId == null) {
            return null;
        }
        return levelRepository.findById(levelId)
                .orElseThrow(() -> new BaseException(LiquorCode.LIQUOR_LEVEL_NOT_FOUND));
    }

    private LiquorType findTypeOrNull(Long typeId) {
        if (typeId == null) {
            return null;
        }
        return typeRepository.findById(typeId)
                .orElseThrow(() -> new BaseException(LiquorCode.LIQUOR_TYPE_NOT_FOUND));
    }


}
