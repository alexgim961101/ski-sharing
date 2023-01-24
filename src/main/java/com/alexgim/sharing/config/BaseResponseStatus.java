package com.alexgim.sharing.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 에러에 대한 상태 표현

@Getter
@AllArgsConstructor
public enum BaseResponseStatus {

    // 1000 : 요청 성공
    SUCCESS(true, 1000, "요청에 성공하셨습니다");

    // 2000 : Request 오류

    // 3000 : Response 오류

    // 4000 : DB 오류

    // 5000 : 서버 오류

    // 기타

    private final boolean isSuccess;
    private final int code;
    private final String message;
}
