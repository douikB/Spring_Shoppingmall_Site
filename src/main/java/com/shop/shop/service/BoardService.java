package com.shop.shop.service;

import com.shop.shop.dto.*;
import com.shop.shop.entity.Board;
import com.shop.shop.entity.Item;
import com.shop.shop.entity.ItemImg;
import com.shop.shop.entity.Member;
import com.shop.shop.repository.BoardRepository;
import com.shop.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    @Value("${file.dir}")
    private String fileDir;

    private final FileService fileService;

    private final BoardRepository boardRepository;

    public Long saveBoard(BoardFormDto boardFormDto, MultipartFile file) throws Exception {
        
        String oriImageName = file.getOriginalFilename();
        String storeImageName = "";
        String imgUrl = "";

        Board board = boardFormDto.createBoard();
        board.setHits(0);
        board.setDeleteYn('N');

        if(!file.isEmpty()) {
            storeImageName = fileService.uploadFile(fileDir, oriImageName, file.getBytes());
            imgUrl = fileDir + storeImageName;

            board.setStoreFileName(storeImageName);
            board.setUploadFileName(oriImageName);
        }

        boardRepository.save(board);

        return board.getId();
    }

    @Transactional(readOnly = true)
<<<<<<< HEAD
    public Page<Board> getBoardList(BoardSearchDto boardSearchDto, Pageable pageable){
        return boardRepository.getBoardList(boardSearchDto, pageable);
=======
<<<<<<< HEAD
    public Page<Board> getBoardList(BoardSearchDto boardSearchDto, Pageable pageable){
        return boardRepository.getBoardList(boardSearchDto, pageable);
=======
    public Page<Board> getBoardList(BoardListDto boardListDto, Pageable pageable){
        return boardRepository.getBoardList(boardListDto, pageable);
>>>>>>> c1f45c957b60397af908407012f2ec8e3437083e
>>>>>>> 5d23f83e1ce9a7e1434bd25d17ead006f6e008c6
    }

    public Long updateBoard(BoardFormDto boardFormDto, MultipartFile file) throws Exception {

        Board board = boardRepository.findById(boardFormDto.getId())
                .orElseThrow(EntityNotFoundException::new);

<<<<<<< HEAD

=======
<<<<<<< HEAD

=======
>>>>>>> c1f45c957b60397af908407012f2ec8e3437083e
>>>>>>> 5d23f83e1ce9a7e1434bd25d17ead006f6e008c6
        String oriImageName = file.getOriginalFilename();
        String storeImageName = "";
        String imgUrl = "";;

        if(!file.isEmpty()) {
            storeImageName = fileService.uploadFile(fileDir, oriImageName, file.getBytes());
            imgUrl = fileDir + storeImageName;

            board.setStoreFileName(storeImageName);
            board.setUploadFileName(oriImageName);
        }

<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
        board.updateBoard(boardFormDto);

>>>>>>> c1f45c957b60397af908407012f2ec8e3437083e
>>>>>>> 5d23f83e1ce9a7e1434bd25d17ead006f6e008c6
        return board.getId();
    }

    @Transactional
    public BoardFormDto getBoardDtl(Long boardId){
        Board board = boardRepository.findById(boardId)
                .orElseThrow(EntityNotFoundException::new);

        int currHits = board.getHits();
        board.setHits(++currHits);

        BoardFormDto boardFormDto = BoardFormDto.of(board);


        return boardFormDto;
    }

    @Transactional
    public void deleteBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(EntityNotFoundException::new);

        board.setDeleteYn('Y');
    }

    public Board findById(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(EntityNotFoundException::new);
    }
}
