package com.shop.shop.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
<<<<<<< HEAD
import com.shop.shop.dto.BoardSearchDto;
=======
<<<<<<< HEAD
import com.shop.shop.dto.BoardSearchDto;
=======
import com.shop.shop.dto.BoardListDto;
>>>>>>> c1f45c957b60397af908407012f2ec8e3437083e
>>>>>>> 5d23f83e1ce9a7e1434bd25d17ead006f6e008c6
import com.shop.shop.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 5d23f83e1ce9a7e1434bd25d17ead006f6e008c6
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
<<<<<<< HEAD
=======
=======

import javax.persistence.EntityManager;
>>>>>>> c1f45c957b60397af908407012f2ec8e3437083e
>>>>>>> 5d23f83e1ce9a7e1434bd25d17ead006f6e008c6
import java.util.List;

public class BoardRepositoryCustomImpl implements BoardRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public BoardRepositoryCustomImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 5d23f83e1ce9a7e1434bd25d17ead006f6e008c6
    private BooleanExpression regDtsAfter(String searchDateType){

        LocalDateTime dateTime = LocalDateTime.now();

        if(StringUtils.equals("all", searchDateType) || searchDateType == null){
            return null;
        } else if(StringUtils.equals("1d", searchDateType)){
            dateTime = dateTime.minusDays(1);
        } else if(StringUtils.equals("1w", searchDateType)){
            dateTime = dateTime.minusWeeks(1);
        } else if(StringUtils.equals("1m", searchDateType)){
            dateTime = dateTime.minusMonths(1);
        } else if(StringUtils.equals("6m", searchDateType)){
            dateTime = dateTime.minusMonths(6);
        }

        return QBoard.board.regTime.after(dateTime);
    }

    private BooleanExpression searchByLike(String searchBy, String searchQuery) {

        if (StringUtils.equals("title", searchBy)) {
            return QBoard.board.title.like("%" + searchQuery + "%");
        } else if (StringUtils.equals("writer", searchBy)) {
            return QBoard.board.writer.like("%" + searchQuery + "%");
        }

        return null;
    }

    @Override
    public Page<Board> getBoardList(BoardSearchDto boardSearchDto, Pageable pageable) {
        QueryResults<Board> results = queryFactory
                .selectFrom(QBoard.board)
                .where(regDtsAfter(boardSearchDto.getSearchDateType()),
                        searchByLike(boardSearchDto.getSearchBy(), boardSearchDto.getSearchQuery()),
                        QBoard.board.deleteYn.eq('N'))
<<<<<<< HEAD
=======
=======

    @Override
    public Page<Board> getBoardList(BoardListDto boardListDto, Pageable pageable) {
        QueryResults<Board> results = queryFactory
                .selectFrom(QBoard.board)
                .where(QBoard.board.deleteYn.eq('N'))
>>>>>>> c1f45c957b60397af908407012f2ec8e3437083e
>>>>>>> 5d23f83e1ce9a7e1434bd25d17ead006f6e008c6
                .orderBy(QBoard.board.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Board> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }
}
