package com.challang.backend.liquor.dto.request;

import com.challang.backend.tag.dto.request.LiquorTagRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;

public record LiquorCreateRequest(

        @NotBlank(message = "술 이름은 필수입니다.")
        String name,

        // TODO: 나중에 필수로 추가하기
        String imageUrl,

        @NotBlank(message = "베이스는 필수입니다. 어떤 원료로 만들어졌는지 입력해주세요. (예: 쌀, 보리 등)")
        String base,

        @NotBlank(message = "유래는 필수입니다. 간략한 술의 역사나 기원을 입력해주세요.")
        String origin,

        @NotBlank(message = "색상은 필수입니다.")
        String color,

        @NotNull(message = "최소 도수는 필수입니다.")
        Double minAbv,

        @NotNull(message = "최대 도수는 필수입니다.")
        Double maxAbv,

        @NotNull(message = "도수 등급 ID는 필수입니다.")
        Long levelId,

        @NotNull(message = "주종 ID는 필수입니다.")
        Long typeId,

        @NotNull(message = "태그 목록은 필수입니다.")
        @Size(min = 1, message = "최소 한 개 이상의 태그를 선택해주세요.")
        List<@Valid LiquorTagRequest> liquorTags
) {
}
