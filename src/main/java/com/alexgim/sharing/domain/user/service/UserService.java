package com.alexgim.sharing.domain.user.service;

import com.alexgim.sharing.common.BaseException;
import com.alexgim.sharing.domain.user.data.dto.UserDetailResDto;
import com.alexgim.sharing.domain.user.data.dto.UserDto;
import com.alexgim.sharing.domain.user.data.dto.UserResDto;

import java.util.List;

public interface UserService {

    /**
     * DB에 회원 정보를 저장
     * input : 회원 가입 폼으로 받은 유저 정보
     * return : 유저의 PK, 닉네임, 이메일
     * 주의 사항 : 회원 권한 설정을 NORMAL로 해줄 것!!
     * */
    UserResDto save(UserDto userDto) throws BaseException;

    /**
     * 특정 회원에 대한 정보 조회
     * input : 특정 회원의 nickname
     * return : 특정 회원에 대한 정보
     * */
    UserDetailResDto readOne(Long userId) throws BaseException;


    /**
     * 전제 회원 정보 조회
     * input : X
     * return : 전체 회원들에 대한 간단한 정보
     * 주의 사항 : 전제회원 조회는 관리자만 가능하도록 설정할 것 -> 컨트롤러 단에서 해결하는게 좋을 듯
     * */
    List<UserResDto> readAll() throws BaseException;



}
