package com.challang.backend.tag.dto.response;

import com.challang.backend.tag.entity.LiquorTag;

public record LiquorTagResponse(
        Long id,
        Long tagId,
        String tagName,
        boolean isCore
) {

    public static LiquorTagResponse fromEntity(LiquorTag liquorTag) {
        return new LiquorTagResponse(
                liquorTag.getId(),
                liquorTag.getTag().getId(),
                liquorTag.getTag().getName(),
                liquorTag.isCore()
        );
    }

}
