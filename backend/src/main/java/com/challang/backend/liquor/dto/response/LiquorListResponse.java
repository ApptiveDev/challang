package com.challang.backend.liquor.dto.response;

import java.util.List;

public record LiquorListResponse(
        List<LiquorResponse> items,
        String nextCursorName,
        boolean hasNext) {
}

