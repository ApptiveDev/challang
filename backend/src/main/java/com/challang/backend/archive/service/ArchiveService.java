package com.challang.backend.archive.service;

import com.challang.backend.archive.code.ArchiveCode;
import com.challang.backend.archive.dto.response.ArchiveListResponse;
import com.challang.backend.archive.entity.Archive;
import com.challang.backend.archive.repository.ArchiveRepository;
import com.challang.backend.global.exception.BaseException;
import com.challang.backend.liquor.code.LiquorCode;
import com.challang.backend.liquor.entity.Liquor;
import com.challang.backend.liquor.repository.LiquorRepository;
import com.challang.backend.user.entity.User;
import com.challang.backend.user.exception.UserErrorCode;
import com.challang.backend.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArchiveService {

    private final ArchiveRepository archiveRepository;
    private final UserRepository userRepository;
    private final LiquorRepository liquorRepository;


    // 아카이브 읽기
    @Transactional(readOnly = true)
    public ArchiveListResponse findAll(Long userId, Long cursorId, String keyword, Integer pageSize) {
        Pageable pageable = PageRequest.of(0, pageSize + 1);

        List<Liquor> liquors = archiveRepository.findArchivedLiquorsByCursor(userId, keyword, cursorId, pageable);

        boolean hasNext = liquors.size() > pageSize;
        if (hasNext) {
            liquors = liquors.subList(0, pageSize);
        }

        Long nextCursor = hasNext ? liquors.get(liquors.size() - 1).getId() : null;
        return new ArchiveListResponse(liquors, nextCursor, hasNext);
    }


    // 아카이브 생성
    public void archiveLiquor(Long userId, Long liquorId) {
        User user = getUserById(userId);
        Liquor liquor = getLiquorById(liquorId);

        if (archiveRepository.existsByUserAndLiquor(user, liquor)) {
            throw new BaseException(ArchiveCode.ALREADY_ARCHIVED);
        }

        Archive archive = Archive.builder()
                .user(user)
                .liquor(liquor)
                .build();
        archiveRepository.save(archive);
    }

    // 아카이브 삭제
    public void unarchiveLiquor(Long userId, Long liquorId) {
        User user = getUserById(userId);
        Liquor liquor = getLiquorById(liquorId);
        int deletedCount = archiveRepository.deleteByUserAndLiquor(user, liquor);
        if (deletedCount == 0) {
            throw new BaseException(ArchiveCode.ARCHIVE_NOT_FOUND);
        }
    }


    private User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BaseException(UserErrorCode.USER_NOT_FOUND));
    }

    private Liquor getLiquorById(Long liquorId) {
        return liquorRepository.findById(liquorId)
                .orElseThrow(() -> new BaseException(LiquorCode.LIQUOR_NOT_FOUND));
    }
}
