package com.alexgim.sharing.handler;

import com.alexgim.sharing.web.dto.BaseResponseDto;
import com.alexgim.sharing.handler.ex.BaseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public BaseResponseDto allExceptionHandler(BaseException e) {
        return new BaseResponseDto(e);
    }
}
