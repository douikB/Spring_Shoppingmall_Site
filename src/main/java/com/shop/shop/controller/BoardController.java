package com.shop.shop.controller;

import com.shop.shop.dto.BoardFormDto;
import com.shop.shop.dto.BoardListDto;
<<<<<<< HEAD
import com.shop.shop.dto.BoardSearchDto;
=======
<<<<<<< HEAD
import com.shop.shop.dto.BoardSearchDto;
=======
>>>>>>> c1f45c957b60397af908407012f2ec8e3437083e
>>>>>>> 5d23f83e1ce9a7e1434bd25d17ead006f6e008c6
import com.shop.shop.dto.ItemFormDto;
import com.shop.shop.entity.Board;
import com.shop.shop.entity.Member;
import com.shop.shop.repository.MemberRepository;
import com.shop.shop.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    private final MemberRepository memberRepository;

    @Value("${file.dir}")
    private String fileDir;

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 5d23f83e1ce9a7e1434bd25d17ead006f6e008c6
    @GetMapping(value = {"/list", "/list/{page}"})
    public String showList(BoardSearchDto boardSearchDto, @PathVariable("page") Optional<Integer> page, Model model){

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 10);
        Page<Board> boards = boardService.getBoardList(boardSearchDto, pageable);

        model.addAttribute("boards", boards);
        model.addAttribute("boardListDto", boardSearchDto);
<<<<<<< HEAD
=======
=======
    @GetMapping("/list")
    public String showList(BoardListDto boardListDto, @PathVariable("page") Optional<Integer> page, Model model){

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 10);
        Page<Board> boards = boardService.getBoardList(boardListDto, pageable);

        model.addAttribute("boards", boards);
        model.addAttribute("boardListDto", boardListDto);
>>>>>>> c1f45c957b60397af908407012f2ec8e3437083e
>>>>>>> 5d23f83e1ce9a7e1434bd25d17ead006f6e008c6
        model.addAttribute("maxPage", 5);

        return "board/boardList";
    }

    @GetMapping("/write")
    public String boardForm(Model model) {
        model.addAttribute("boardFormDto", new BoardFormDto());
        return "board/writeForm";
    }

    @GetMapping("/modify/{boardId}")
    public String modifyBoard(@PathVariable("boardId") Long boardId, Model model) {

        try {
            BoardFormDto boardFormDto = boardService.getBoardDtl(boardId);
            model.addAttribute("boardFormDto", boardFormDto);
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "존재하지 않는 게시글 입니다.");
            return "redirect:/board/list";
        }
        return "board/writeForm";
    }

    @GetMapping("/delete/{boardId}")
    public String deleteBoard(@PathVariable("boardId") Long boardId, Model model) {

        try {
            boardService.deleteBoard(boardId);
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "삭제 도중에 오류가 발생했습니다.");
            return "board/writeForm";
        }
        return "redirect:/board/list";
    }

    @PostMapping("/write")
    public String newBoard(@Valid BoardFormDto boardFormDto, BindingResult bindingResult,
                           Model model, HttpServletRequest request,
                           @RequestParam MultipartFile file) throws Exception {

        if(boardFormDto.getTitle().isEmpty()){
            model.addAttribute("errorMessage", "제목을 입력해주세요.");
            return "board/writeForm";
        } else if (boardFormDto.getContent().isEmpty()) {
            model.addAttribute("errorMessage", "내용을 입력해주세요.");
            return "board/writeForm";
        }

        if(bindingResult.hasErrors()){
            return "board/writeForm";
        }

        Member member = memberRepository.findByEmail(request.getUserPrincipal().getName());
        boardFormDto.setWriter(member.getName());

        try {
            boardService.saveBoard(boardFormDto, file);
        } catch (Exception e){
            model.addAttribute("errorMessage", "게시글 등록 중 에러가 발생하였습니다.");
            return "board/writeForm";
        }

        return "redirect:/board/list";
    }

    @PostMapping("/modify/{boardId}")
    public String updateBoard(@PathVariable("boardId") Long boardId,
                              @Valid BoardFormDto boardFormDto, BindingResult bindingResult,
                              Model model, HttpServletRequest request,
                              @RequestParam MultipartFile file) throws Exception {

        if(boardFormDto.getTitle().isEmpty()){
            model.addAttribute("errorMessage", "제목을 입력해주세요.");
            return "board/writeForm";
        } else if (boardFormDto.getContent().isEmpty()) {
            model.addAttribute("errorMessage", "내용을 입력해주세요.");
            return "board/writeForm";
        }

        if(bindingResult.hasErrors()){
            return "board/writeForm";
        }

        Member member = memberRepository.findByEmail(request.getUserPrincipal().getName());
        boardFormDto.setWriter(member.getName());

        try {
            boardService.updateBoard(boardFormDto, file);
        } catch (Exception e){
            model.addAttribute("errorMessage", "게시글 수정 중 에러가 발생하였습니다.");
            return "board/writeForm";
        }

        return "redirect:/board/list";
    }

    @GetMapping(value = "/view/{viewId}")
    public String boardDtl(@PathVariable("viewId") Long boardId, Model model, HttpServletRequest request){

        try {
            model.addAttribute("user", request.getUserPrincipal().getName());
        } catch (Exception e) {
            model.addAttribute("errorMessage", "로그인해 주세요.");
            return "redirect:/members/login";
        }

        try {
            BoardFormDto boardFormDto = boardService.getBoardDtl(boardId);
            model.addAttribute("boardFormDto", boardFormDto);
            model.addAttribute("fileDir", fileDir);
        } catch(EntityNotFoundException e){
            model.addAttribute("errorMessage", "존재하지 않는 게시글 입니다.");
            return "redirect:/board/list";
        }

        return "board/viewForm";
    }

    //이미지 보여주기
    @ResponseBody
    @GetMapping("/images/{fileName}")
    public Resource downloadImage(@PathVariable String fileName) throws MalformedURLException {
        return new UrlResource("file:" + fileDir + fileName);
    }

    //파일 다운로드
    @GetMapping("/attach/{boardId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long boardId) throws MalformedURLException, UnsupportedEncodingException {
        Board board = boardService.findById(boardId);
        String storeFileName = board.getStoreFileName();
        //파일 이름 인코딩해야함
        String uploadFileName = URLEncoder.encode(board.getUploadFileName(), "UTF-8").replace("+", "%20");

        UrlResource urlResource = new UrlResource("file:" + fileDir + storeFileName);

        log.info("uploadFileName={}", uploadFileName);
        String contentDisposition = "attachment; filename=\"" + uploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(urlResource);
    }



}
