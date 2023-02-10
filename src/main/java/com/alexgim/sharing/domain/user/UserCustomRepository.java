package com.alexgim.sharing.domain.user;

import com.alexgim.sharing.web.dto.user.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserCustomRepository {
    User findByUsername(String username);

    Page<User> pageUserAll(Pageable pageable);
}
