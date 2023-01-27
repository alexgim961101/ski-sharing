package com.alexgim.sharing.domain.user.service.impl;

import com.alexgim.sharing.common.BaseException;
import com.alexgim.sharing.common.BaseResponseStatus;
import com.alexgim.sharing.domain.user.data.dto.UserDto;
import com.alexgim.sharing.domain.user.data.dto.UserResDto;
import com.alexgim.sharing.domain.user.data.entity.User;
import com.alexgim.sharing.domain.user.data.repository.UserRepository;
import com.alexgim.sharing.domain.user.service.UserService;
import com.alexgim.sharing.domain.user.data.dto.UserDetailResDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResDto save(UserDto userDto) throws BaseException {
        // status 상태를 Dto에서 넣어줌
        User user = userDto.toEntity();
        User save = null;
        try {
            save = userRepository.save(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BaseException(BaseResponseStatus.DB_CONNECTION_ERROR);
        }

        return UserResDto.builder()
                .userId(save.getUserId())
                .nickname(save.getNickname())
                .img(save.getImg())
                .email(save.getEmail()).build();
    }

    @Override
    public UserDetailResDto readOne(Long userId) throws BaseException {
        User user = null;
        try {
            logger.info("--- readOne 메서드 작동 , userId : {}", userId);
            user = userRepository.findById(userId).get();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BaseException(BaseResponseStatus.DB_CONNECTION_ERROR);
        }
        logger.info("user 정보 : {}", user.toString());
        return UserDetailResDto.builder()
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .img(user.getImg())
                .addr(user.getAddr())
                .content(user.getContent())
                .build();
    }

    @Override
    public List<UserResDto> readAll() throws BaseException {
        List<User> all = null;
        try {
            all = userRepository.findAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BaseException(BaseResponseStatus.DB_CONNECTION_ERROR);
        }

        List<UserResDto> list = new ArrayList<>();
        for(User user : all) {
            list.add(UserResDto.builder()
                    .userId(user.getUserId())
                    .nickname(user.getNickname())
                    .img(user.getImg())
                    .email(user.getEmail()).build());
        }

        return list;
    }
}
