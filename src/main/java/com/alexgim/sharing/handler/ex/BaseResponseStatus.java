package com.alexgim.sharing.handler.ex;

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
    DB_DUPLICATED_NICKNAME(false, 4001, "이미 아이디가 존재합니다"),

    DB_SAVE_FAILED(false, 4001, "DB 저장에 실패하였습니다."),

    // 5000 : 서버 오류

    // 기타
    AWS_S3_CONNECTION_FAILED(false,6000, "AWS S3 연결에 실패하였습니다");

    private final boolean isSuccess;
    private final int code;
    private final String message;
}