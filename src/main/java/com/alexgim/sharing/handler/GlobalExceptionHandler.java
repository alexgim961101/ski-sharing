package com.alexgim.sharing.handler;

import com.alexgim.sharing.handler.ex.BaseResponseStatus;
import com.alexgim.sharing.web.dto.BaseResponseDto;
import com.alexgim.sharing.handler.ex.BaseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public BaseResponseDto allExceptionHandler(BaseException e) {
        return new BaseResponseDto(e.getStatus(), e.getErrorMap());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public BaseResponseDto violationHandler(ConstraintViolationException e) {
        return new BaseResponseDto(BaseResponseStatus.INVALID_REQUEST, e.getLocalizedMessage());
    }
}
