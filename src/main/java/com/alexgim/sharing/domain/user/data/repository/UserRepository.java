package com.alexgim.sharing.domain.user.data.repository;

import com.alexgim.sharing.domain.user.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.nickname = :nickname")
    List<User> findByNicknameIs(@Param("nickname") String nickname);
}
