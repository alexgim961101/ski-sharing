package com.alexgim.sharing.service.user;

import com.alexgim.sharing.domain.user.User;
import com.alexgim.sharing.web.dto.user.UserDto;
import com.alexgim.sharing.web.dto.user.UserLoginReqDto;
import com.alexgim.sharing.web.dto.user.UserUpdateProfileDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    /**
     * 회원가입 로직
     * 1. 엔티티 저장 (OK)
     * 2. 비밀번호는 암호화 하여 저장할 것 (OK)
     * 3. StatusType 부여 (OK)
     * 4. 아이디 중복 체크 (OK)
     * */
    User enroll(UserLoginReqDto userLoginReqDto);

    /**
     * 프로필 사진 변경 로직
     * 1.
     * */
    User updateImg(Long userId, UserUpdateProfileDto userUpdateProfileDto);

    
    /**
     * 자기소개 변경 로직
     * */
    User updateContent(Long userId, String content);

    List<UserDto> readAll(Pageable pageable);
}
