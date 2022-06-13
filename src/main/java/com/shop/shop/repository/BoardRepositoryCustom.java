package com.shop.shop.repository;

import com.shop.shop.dto.BoardListDto;
import com.shop.shop.dto.ItemSearchDto;
import com.shop.shop.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {

    Page<Board> getBoardList(BoardListDto boardListDto, Pageable pageable);

    //Page<Board> getBoardList(BoardListDto boardListDto, Pageable pageable);

}
