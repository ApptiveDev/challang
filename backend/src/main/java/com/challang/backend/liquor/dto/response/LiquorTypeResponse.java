package com.challang.backend.liquor.dto.response;

import com.challang.backend.liquor.entity.LiquorType;

public record LiquorTypeResponse(
        Long id,
        String name
) {
    public static LiquorTypeResponse fromEntity(LiquorType type) {
        return new LiquorTypeResponse(type.getId(), type.getName());
    }

}
