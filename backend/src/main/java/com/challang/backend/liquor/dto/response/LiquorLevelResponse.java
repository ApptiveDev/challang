package com.challang.backend.liquor.dto.response;


import com.challang.backend.liquor.entity.LiquorLevel;

public record LiquorLevelResponse(
        Long id,
        String name
) {
    public static LiquorLevelResponse fromEntity(LiquorLevel level) {
        return new LiquorLevelResponse(level.getId(), level.getName());
    }
}
