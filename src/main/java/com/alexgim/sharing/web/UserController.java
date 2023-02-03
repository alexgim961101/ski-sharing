package com.alexgim.sharing.web;

import com.alexgim.sharing.domain.user.User;
import com.alexgim.sharing.handler.ex.BaseException;
import com.alexgim.sharing.handler.ex.BaseResponseStatus;
import com.alexgim.sharing.service.user.UserService;
import com.alexgim.sharing.web.dto.BaseResponseDto;
import com.alexgim.sharing.web.dto.user.UserLoginReqDto;
import com.alexgim.sharing.web.dto.user.UserUpdateProfileDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    /**
     * 회원가입 API
     * 1. 정보들은 x-www-form-urlencoded로 들어올 것
     * */
    @ApiOperation(value = "회원가입", notes = "유저 정보를 받아서 DB에 저장하는 API")
    @PostMapping
    public BaseResponseDto signup(@Valid UserLoginReqDto userLoginReqDto, BindingResult bindingResult) {
        checkValidation(bindingResult);

        User userEntity = userService.enroll(userLoginReqDto);
        return new BaseResponseDto(userEntity);
    }

    /**
     * 회원 프로필 사진 수정 API
     * 1. 정보들은 form-data로 들어올 것
     * */
    @ApiOperation(value = "프로필 사진 변경", notes = "프로필 이미지 변경을 위한 API")
    @PutMapping("/{userId}/img")
    public BaseResponseDto updateProfile(@Min(1) @PathVariable Long userId, UserUpdateProfileDto userUpdateProfileDto, BindingResult bindingResult) {
        checkValidation(bindingResult);

        User userEntity = userService.updateImg(userId, userUpdateProfileDto);
        return new BaseResponseDto(userEntity);
    }

    /**
     * 자기소개 업데이트
     * 질문1 : content 뒤에 BindingResult를 파라미터로 넣으면 오류가 발생 -> DTO를 사용하면 문제가 없긴 하지만 다른 방법이 없을까? -> RequestBody 이용
     * 질문2 : bindingresult는 바로 옆 객체의 오류만 받음 -> 한번에 받는 다른 방법은 없나? -> 일단 글로벌 예외처리로 해결
     * 질문3 : String값을 requestBody로 받으니 JSON 형태로 출력됨 해결 방법은? -> Map사용할 수 있지만 swagger 연결이 힘들듯 + bindingResult 처리도 힘듬
     * */
    @ApiOperation(value = "자기소개 변경", notes = "자기소개 변경을 위한 API")
    @PutMapping("/{userId}/content")
    public BaseResponseDto updateContent(@Min(1) @PathVariable Long userId, @ApiParam(value = "자기소개", required = false) @Size(min = 0, max = 300) @RequestBody String content, BindingResult bindingResult) {
        checkValidation(bindingResult);

        User userEntity = userService.updateContent(userId, content);
        return new BaseResponseDto(userEntity);
    }


    public void checkValidation(BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new BaseException(BaseResponseStatus.INVALID_REQUEST, errorMap);
        }
    }

}
