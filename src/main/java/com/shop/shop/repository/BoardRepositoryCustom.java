package com.shop.shop.repository;

<<<<<<< HEAD
import com.shop.shop.dto.BoardSearchDto;
=======
import com.shop.shop.dto.BoardListDto;
import com.shop.shop.dto.ItemSearchDto;
>>>>>>> c1f45c957b60397af908407012f2ec8e3437083e
import com.shop.shop.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {

<<<<<<< HEAD
    Page<Board> getBoardList(BoardSearchDto boardSearchDto, Pageable pageable);
=======
    Page<Board> getBoardList(BoardListDto boardListDto, Pageable pageable);
>>>>>>> c1f45c957b60397af908407012f2ec8e3437083e

    //Page<Board> getBoardList(BoardListDto boardListDto, Pageable pageable);

}
