package com.alexgim.sharing.domain.board;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.alexgim.sharing.domain.board.QBoard.board;

@Repository
@RequiredArgsConstructor
public class BoardCustomRepositoryImpl implements BoardCustomRepository{

    private final JPAQueryFactory queryFactory;
    @Override
    public Page<Board> findAllBoard10Size(Pageable pageable) {
        QueryResults<Board> boardQueryResults = queryFactory.selectFrom(board)
                .orderBy(board.updatedAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Board> results = boardQueryResults.getResults();
        long total = boardQueryResults.getTotal();

        return new PageImpl<>(results, pageable, total);
    }
}
