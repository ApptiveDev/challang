package com.challang.backend.auth.dto.response;

import com.fasterxml.jackson.annotation.*;


@JsonIgnoreProperties(ignoreUnknown = true)
public record KakaoUserInfoResponseDto(

        //회원 번호
        @JsonProperty("id")
        Long id,

        //카카오 계정 정보
        @JsonProperty("kakao_account")
        KakaoAccount kakaoAccount
) {


    @JsonIgnoreProperties(ignoreUnknown = true)
    public record KakaoAccount(
            @JsonProperty("profile")
            Profile profile
            ) {

        @JsonIgnoreProperties(ignoreUnknown = true)
        public record Profile(
                //닉네임
                @JsonProperty("nickname")
                String nickName,

                //프로필 사진 URL
                @JsonProperty("profile_image_url")
                String profileImageUrl
        ) {

        }
    }


}