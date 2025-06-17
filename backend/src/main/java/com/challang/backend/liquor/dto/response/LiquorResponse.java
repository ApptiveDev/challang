package com.challang.backend.liquor.dto.response;

import com.challang.backend.liquor.entity.Liquor;
import com.challang.backend.tag.dto.response.LiquorTagResponse;
import java.util.List;


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
        String typeName,
        List<LiquorTagResponse> liquorTags
) {
    public static LiquorResponse fromEntity(Liquor liquor) {
        List<LiquorTagResponse> liquorTags = liquor.getLiquorTags().stream()
                .map(LiquorTagResponse::fromEntity)
                .toList();

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
                liquor.getType().getName(),
                liquorTags
        );
    }

}
