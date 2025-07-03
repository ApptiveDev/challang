package com.challang.backend.liquor.service;


import com.challang.backend.global.exception.BaseException;
import com.challang.backend.liquor.code.LiquorCode;
import com.challang.backend.liquor.dto.request.LiquorLevelRequest;
import com.challang.backend.liquor.dto.response.LiquorLevelResponse;
import com.challang.backend.liquor.entity.LiquorLevel;
import com.challang.backend.liquor.repository.LiquorLevelRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LiquorLevelService {

    private final LiquorLevelRepository liquorLevelRepository;

    // 생성
    public LiquorLevelResponse create(LiquorLevelRequest request) {
        if (liquorLevelRepository.existsByName(request.name())) {
            throw new BaseException(LiquorCode.LIQUOR_LEVEL_ALREADY_EXISTS);
        }

        LiquorLevel level = new LiquorLevel(request.name());
        LiquorLevel saved = liquorLevelRepository.save(level);
        return LiquorLevelResponse.fromEntity(saved);
    }

    // 단건 조회
    @Transactional(readOnly = true)
    public LiquorLevelResponse findById(Long id) {
        return LiquorLevelResponse.fromEntity(getLiquorLevelById(id));
    }

    // 전체 조회
    @Transactional(readOnly = true)
    public List<LiquorLevelResponse> findAll() {
        return liquorLevelRepository.findAll().stream()
                .map(LiquorLevelResponse::fromEntity)
                .toList();
    }

    // 주종 수정
    public LiquorLevelResponse update(Long id, LiquorLevelRequest request) {
        LiquorLevel level = getLiquorLevelById(id);

        if (liquorLevelRepository.existsByName(request.name()) &&
                !level.getName().equals(request.name())) {
            throw new BaseException(LiquorCode.LIQUOR_LEVEL_ALREADY_EXISTS);
        }

        level.update(request);
        return LiquorLevelResponse.fromEntity(level);
    }

    // 주종 삭제
    public void delete(Long id) {
        LiquorLevel level = getLiquorLevelById(id);
        liquorLevelRepository.delete(level);
    }

    private LiquorLevel getLiquorLevelById(Long id) {
        return liquorLevelRepository.findById(id)
                .orElseThrow(() -> new BaseException(LiquorCode.LIQUOR_LEVEL_NOT_FOUND));
    }


}
