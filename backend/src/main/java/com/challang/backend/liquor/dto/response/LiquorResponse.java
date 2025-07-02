package com.challang.backend.liquor.dto.response;

import com.challang.backend.liquor.entity.Liquor;
import com.challang.backend.tag.dto.response.LiquorTagResponse;
import java.util.List;


public record LiquorResponse(
        Long id,
        String name,
        String imageUrl,
        String tastingNote,
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
    public static LiquorResponse fromEntity(Liquor liquor, String s3BaseUrl) {
        List<LiquorTagResponse> liquorTags = liquor.getLiquorTags().stream()
                .map(LiquorTagResponse::fromEntity)
                .toList();

        String fullImageUrl = s3BaseUrl + "/" + liquor.getImageUrl();

        return new LiquorResponse(
                liquor.getId(),
                liquor.getName(),
                fullImageUrl,
                liquor.getTastingNote(),
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
