package com.alexgim.sharing.handler.ex;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseException extends RuntimeException{
    private BaseResponseStatus status;
    private Map<String, String> errorMap;

    public BaseException(BaseResponseStatus status) {
        this.status = status;
    }
}
