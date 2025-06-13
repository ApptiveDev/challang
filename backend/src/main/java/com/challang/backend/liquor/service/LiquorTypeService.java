package com.challang.backend.liquor.service;

import com.challang.backend.global.exception.BaseException;
import com.challang.backend.liquor.dto.request.LiquorTypeRequest;
import com.challang.backend.liquor.dto.response.LiquorTypeResponse;
import com.challang.backend.liquor.entity.LiquorType;
import com.challang.backend.liquor.code.LiquorCode;
import com.challang.backend.liquor.repository.LiquorTypeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LiquorTypeService {

    private final LiquorTypeRepository liquorTypeRepository;

    // 주종 추가
    public LiquorTypeResponse create(LiquorTypeRequest request) {
        if (liquorTypeRepository.existsByName(request.name())) {
            throw new BaseException(LiquorCode.LIQUOR_TYPE_ALREADY_EXISTS);
        }

        LiquorType type = new LiquorType(request.name());
        LiquorType saved = liquorTypeRepository.save(type);
        return LiquorTypeResponse.fromEntity(saved);
    }

    // 주종 조회
    @Transactional(readOnly = true)
    public LiquorTypeResponse findById(Long id) {
        return LiquorTypeResponse.fromEntity(getLiquorTypeById(id));
    }

    // 주종 전체 조회
    @Transactional(readOnly = true)
    public List<LiquorTypeResponse> findAll() {
        return liquorTypeRepository.findAll().stream()
                .map(LiquorTypeResponse::fromEntity)
                .toList();
    }

    // 주종 수정
    public LiquorTypeResponse update(Long id, LiquorTypeRequest request) {
        LiquorType type = getLiquorTypeById(id);

        if (liquorTypeRepository.existsByName(request.name()) &&
                !type.getName().equals(request.name())) {
            throw new BaseException(LiquorCode.LIQUOR_TYPE_ALREADY_EXISTS);
        }

        type.update(request);
        return LiquorTypeResponse.fromEntity(type);
    }

    // 주종 삭제
    public void delete(Long id) {
        LiquorType type = getLiquorTypeById(id);
        liquorTypeRepository.delete(type);
    }

    private LiquorType getLiquorTypeById(Long id) {
        return liquorTypeRepository.findById(id)
                .orElseThrow(() -> new BaseException(LiquorCode.LIQUOR_TYPE_NOT_FOUND));
    }
}
