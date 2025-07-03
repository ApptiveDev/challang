package com.challang.backend.tag.service;

import com.challang.backend.global.exception.BaseException;
import com.challang.backend.tag.code.TagCode;
import com.challang.backend.tag.dto.request.TagRequest;
import com.challang.backend.tag.dto.response.TagResponse;
import com.challang.backend.tag.entity.Tag;
import com.challang.backend.tag.repository.TagRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TagService {

    private final TagRepository tagRepository;

    // 태그 생성
    public TagResponse create(TagRequest request) {
        if (tagRepository.existsByName(request.name())) {
            throw new BaseException(TagCode.TAG_ALREADY_EXISTS);
        }

        Tag tag = new Tag(request.name());
        Tag saved = tagRepository.save(tag);
        return TagResponse.fromEntity(saved);
    }

    // 태그 단건 조회
    @Transactional(readOnly = true)
    public TagResponse findById(Long id) {
        return TagResponse.fromEntity(getTagById(id));
    }

    // 태그 전체 조회
    @Transactional(readOnly = true)
    public List<TagResponse> findAll() {
        return tagRepository.findAll().stream()
                .map(TagResponse::fromEntity)
                .toList();
    }

    // 태그 수정
    public TagResponse update(Long id, TagRequest request) {
        Tag tag = getTagById(id);

        if (tagRepository.existsByName(request.name()) &&
                !tag.getName().equals(request.name())) {
            throw new BaseException(TagCode.TAG_ALREADY_EXISTS);
        }

        tag.update(request);
        return TagResponse.fromEntity(tag);
    }

    // 태그 삭제
    public void delete(Long id) {
        Tag tag = getTagById(id);
        tagRepository.delete(tag);
    }

    private Tag getTagById(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new BaseException(TagCode.TAG_NOT_FOUND));
    }
}
