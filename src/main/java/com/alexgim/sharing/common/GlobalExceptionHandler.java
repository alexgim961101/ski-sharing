package com.alexgim.sharing.common;

import com.alexgim.sharing.config.BaseResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public BaseResponse allExceptionHandler(Exception e) {
        return new BaseResponse(e);
    }
}
