package com.shop.shop.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.shop.dto.BoardListDto;
import com.shop.shop.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

public class BoardRepositoryCustomImpl implements BoardRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public BoardRepositoryCustomImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public Page<Board> getBoardList(BoardListDto boardListDto, Pageable pageable) {
        QueryResults<Board> results = queryFactory
                .selectFrom(QBoard.board)
                .where(QBoard.board.deleteYn.eq('N'))
                .orderBy(QBoard.board.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Board> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }
}
