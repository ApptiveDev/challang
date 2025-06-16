package com.challang.backend.archive.dto.response;

import com.challang.backend.liquor.entity.Liquor;
import java.util.List;

public record ArchiveListResponse(
        List<Liquor> items,
        Long nextCursor,
        boolean hasNext
) {
}
