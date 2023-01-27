package com.alexgim.sharing.common;

import com.alexgim.sharing.config.BaseException;
import com.alexgim.sharing.config.BaseResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public BaseResponse allExceptionHandler(BaseException e) {
        return new BaseResponse(e);
    }
}
