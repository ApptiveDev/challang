package com.challang.backend.liquor.dto.request;

public record LiquorUpdateRequest(

        String name,
        String imageUrl,
        String base,
        String origin,
        String color,
        Double minAbv,
        Double maxAbv,
        Long levelId,
        Long typeId
) {
}
