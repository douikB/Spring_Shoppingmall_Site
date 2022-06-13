package com.shop.shop.dto;

import com.shop.shop.entity.Board;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter @Setter
public class BoardFormDto {

    private Long id; //PK

    @NotBlank(message = "제목을 입력해 주세요.")
    private String title; //제목
    @NotBlank(message = "내용을 입력해 주세요.")
    private String content; //내용
    private int hits; //조회수
    private char deleteYn; //삭제 여부
    private String uploadFileName;
    private String storeFileName;
    private String writer;
    private String createdBy;
    private LocalDateTime regTime;

    private static ModelMapper modelMapper = new ModelMapper();

    public Board createBoard() {
        return modelMapper.map(this, Board.class);
    }


    public static BoardFormDto of(Board board){
        return modelMapper.map(board, BoardFormDto.class);
    }

}
