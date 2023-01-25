package com.alexgim.sharing.user.controller;

import com.alexgim.sharing.config.BaseException;
import com.alexgim.sharing.config.BaseResponse;
import com.alexgim.sharing.config.BaseResponseStatus;
import com.alexgim.sharing.user.data.dto.UserDetailResDto;
import com.alexgim.sharing.user.data.dto.UserDto;
import com.alexgim.sharing.user.data.dto.UserResDto;
import com.alexgim.sharing.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 회원가입 API
     * input : UserDto
     * Output : UserResDto
     * status : 2000, 4000
     * */
    @ApiOperation(value = "회원가입", notes = "UserDto의 정보를 받아 서버에 저장한다.")
    @PostMapping
    public BaseResponse<?> signup(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
        logger.info("------ signup API 실행 -------");

        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError fieldError = (FieldError) objectError;
                logger.error("field : {}, message : {}", fieldError.getField(), fieldError.getDefaultMessage());
            });
            return new BaseResponse(BaseResponseStatus.INVALID_REQUEST);
        }

        UserResDto user = null;
        try {
            user = userService.save(userDto);
        } catch (BaseException e) {
            return new BaseResponse(e);
        }

        return new BaseResponse(user);
    }

    /**
     * 회원조회 API
     * input : String
     * Output : UserDetailResDto
     * status : 2000, 4000
     * */
    @ApiOperation(value = "특정 회원 조회", notes = "닉네임을 이용하여 특정 회원을 조회한다")
    @GetMapping("/{userId}")
    public BaseResponse userDetail(@ApiParam(value = "닉네임", required = true) @PathVariable Long userId) {
        logger.info("---- 특정 회원 조회 API 실행 ----");
//        if(bindingResult.hasErrors()){
//            bindingResult.getAllErrors().forEach(objectError -> {
//                FieldError fieldError = (FieldError) objectError;
//                logger.error("field : {}, message : {}", fieldError.getField(), objectError.getDefaultMessage());
//            });
//            return new BaseResponse(BaseResponseStatus.INVALID_REQUEST);
//        }

        UserDetailResDto user = null;
        try {
            user = userService.readOne(userId);
        } catch (BaseException e) {
            return new BaseResponse(e);
        }

        return new BaseResponse(user);
    }

    /**
     * 회원 전체 조회 API
     * input :
     * output :
     * status :
     * */
    @GetMapping
    public BaseResponse userList() {
        // JWT 토큰을 이용하여 관리자 권한이 있는지 확인 후 API 실행

        List<UserResDto> userResDtos = null;
        try {
            userResDtos = userService.readAll();
        } catch (BaseException e) {
            return new BaseResponse(e);
        }

        return new BaseResponse(userResDtos);
    }
}
