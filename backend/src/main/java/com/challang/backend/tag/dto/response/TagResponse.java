package com.challang.backend.tag.dto.response;

import com.challang.backend.tag.entity.Tag;

public record TagResponse(
        Long id,
        String name
) {
    public static TagResponse fromEntity(Tag tag) {
        return new TagResponse(tag.getId(), tag.getName());
    }
}
