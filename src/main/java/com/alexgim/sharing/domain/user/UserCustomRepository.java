package com.alexgim.sharing.domain.user;

public interface UserCustomRepository {
    User findByUsername(String username);
}
