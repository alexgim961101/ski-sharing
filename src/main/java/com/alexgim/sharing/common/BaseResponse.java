package com.alexgim.sharing.common;

import com.alexgim.sharing.common.BaseResponseStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})  // Json 출력 순서 결정
public class BaseResponse<T> {

    /**
     * final을 쓰는 이유는 무엇일까? 변경되어서는 안되는 값이라는 것을 강조하기 위해서?
     * */

    @JsonProperty("isSuccess")           // BaseResponse 객체를 JSON으로 바꾸면 is_success로 바뀜 => 이걸 isSuccess로 고정시켜줌
    private final Boolean isSuccess;
    private final String message;
    private final int code;
    @JsonInclude(JsonInclude.Include.NON_NULL)    // JsonIgnore의 반대, null이 아닌 값만 받아들인다는 의미
    private T result;

    // 요청 성공
    public BaseResponse(T result) {
        this.isSuccess = BaseResponseStatus.SUCCESS.isSuccess();
        this.message = BaseResponseStatus.SUCCESS.getMessage();
        this.code = BaseResponseStatus.SUCCESS.getCode();
        this.result = result;
    }

    // 요청 실패
    public BaseResponse(BaseResponseStatus status) {
        this.isSuccess = status.isSuccess();
        this.message = status.getMessage();
        this.code = status.getCode();
    }
}
