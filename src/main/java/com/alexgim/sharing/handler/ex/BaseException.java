package com.alexgim.sharing.handler.ex;

import com.alexgim.sharing.web.dto.BaseResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class BaseException extends Exception{
    private BaseResponseStatus status;
}
