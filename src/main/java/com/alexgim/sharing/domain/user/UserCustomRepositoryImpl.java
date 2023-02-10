package com.alexgim.sharing.domain.user;

import com.alexgim.sharing.web.dto.user.UserDto;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.alexgim.sharing.domain.user.QUser.user;

@Repository
@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public Page<User> pageUserAll(Pageable pageable) {
        QueryResults<User> userQueryResults = jpaQueryFactory.selectFrom(user)
                .orderBy(user.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<User> results = userQueryResults.getResults();
        long total = userQueryResults.getTotal();

        return new PageImpl<>(results, pageable, total);
    }

    @Override
    public User findByNickname(String nickname) {
        return jpaQueryFactory.selectFrom(user)
                .where(user.nickname.eq(nickname))
                .fetchOne();
    }
}
