package com.alexgim.sharing.domain.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.alexgim.sharing.domain.user.QUser.user;

@Repository
@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public User findByUsername(String username) {
        return jpaQueryFactory.selectFrom(user)
                .where(user.nickname.eq(username))
                .fetchOne();
    }
}
