package com.challang.backend.util.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> {
    private String messageCode;
    private String messageStatus;
    private T data;

}
