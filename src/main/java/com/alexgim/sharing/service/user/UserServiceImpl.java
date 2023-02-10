package com.alexgim.sharing.service.user;

import com.alexgim.sharing.domain.user.User;
import com.alexgim.sharing.domain.user.UserRepository;
import com.alexgim.sharing.handler.ex.BaseException;
import com.alexgim.sharing.handler.ex.BaseResponseStatus;
import com.alexgim.sharing.util.JwtService;
import com.alexgim.sharing.util.S3Component;
import com.alexgim.sharing.web.dto.user.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final S3Component sc;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtService jwtService;

    @Transactional
    @Override
    public User enroll(UserSignUpReqDto userSignUpReqDto) {
        User user = userSignUpReqDto.toEntity();
        String encode = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        User userEntity = null;
        try {
            userEntity = userRepository.save(user);
        } catch (RuntimeException e) {
            throw new BaseException(BaseResponseStatus.DB_DUPLICATED_NICKNAME);
        }
        return userEntity;
    }

    @Transactional
    @Override
    public User updateImg(Long userId, UserUpdateProfileDto userUpdateProfileDto) {
        try {
            User userEntity1 = userRepository.findById(userId).get();
            String url = sc.saveFile(userUpdateProfileDto.getFile());
            userEntity1.setImg(url);
            User userEntity = userRepository.save(userEntity1);
            return userEntity;
        } catch (RuntimeException e) {
            throw new BaseException(BaseResponseStatus.DB_CONNECTION_ERROR);
        }
    }

    @Transactional
    @Override
    public User updateContent(Long userId, String content) {
        try {
            User user = userRepository.findById(userId).get();
            user.setContent(content);
            User userEntity = userRepository.save(user);
            return userEntity;
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.DB_CONNECTION_ERROR);
        }
    }

    /**
     * 로그인 API (jwt 토큰 이용)
     * */
    @Override
    public PostLoginResp login(PostLoginReq postLoginReq) {
        String nickname = postLoginReq.getNickname();
        User userEntity = null;
        try {
            userEntity = userRepository.findByNickname(nickname);
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.DB_CONNECTION_ERROR);
        }

        if(userEntity == null) throw new BaseException(BaseResponseStatus.INVALID_LOGIN);
        if(!bCryptPasswordEncoder.matches(postLoginReq.getPassword(), userEntity.getPassword())) throw new BaseException(BaseResponseStatus.INVALID_LOGIN);

        String jwt = jwtService.createJwt(userEntity.getId());
        return PostLoginResp.builder()
                .id(userEntity.getId())
                .nickname(nickname)
                .img(userEntity.getImg())
                .jwt(jwt)
                .build();
    }

    @Override
    public List<UserDto> readAll(Pageable pageable) {
        Page<User> users = userRepository.pageUserAll(pageable);
        ArrayList<UserDto> list = new ArrayList<>();
        users.get().forEach(obj -> list.add(obj.toDto()));
        return list;
    }

}
