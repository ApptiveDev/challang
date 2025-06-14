package com.challang.backend.liquor.dto.response;

import com.challang.backend.liquor.entity.Liquor;

// TODO: Tag 추가 예정
public record LiquorResponse(
        Long id,
        String name,
        String imageUrl,
        String base,
        String origin,
        String color,
        Double minAbv,
        Double maxAbv,
        Long levelId,
        String levelName,
        Long typeId,
        String typeName
) {
    public static LiquorResponse fromEntity(Liquor liquor) {
        return new LiquorResponse(
                liquor.getId(),
                liquor.getName(),
                liquor.getImageUrl(),
                liquor.getBase(),
                liquor.getOrigin(),
                liquor.getColor(),
                liquor.getMinAbv(),
                liquor.getMaxAbv(),
                liquor.getLevel().getId(),
                liquor.getLevel().getName(),
                liquor.getType().getId(),
                liquor.getType().getName()
        );
    }

}
