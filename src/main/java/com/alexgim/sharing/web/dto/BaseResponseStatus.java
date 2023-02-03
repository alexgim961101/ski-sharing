package com.alexgim.sharing.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 에러에 대한 상태 표현

@Getter
@AllArgsConstructor
public enum BaseResponseStatus {

    // 1000 : 요청 성공
    SUCCESS(true, 1000, "요청에 성공하셨습니다"),

    // 2000 : Request 오류
    INVALID_REQUEST(false, 2000, "잘못된 요청값입니다"),

    // 3000 : Response 오류

    // 4000 : DB 오류
    DB_CONNECTION_ERROR(false, 4000, "DB 연결에 실패하였습니다"),

    DB_SAVE_FAILED(false, 4001, "DB 저장에 실패하였습니다.");

    // 5000 : 서버 오류

    // 기타

    private final boolean isSuccess;
    private final int code;
    private final String message;
}
