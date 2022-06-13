package com.shop.shop.dto;

import com.shop.shop.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class BoardDto {

    private Long id; //PK
    private String title; //제목
    private String content; //내용
    private int hits; //조회수
    private String writer;
    private String uploadFileName; //업로드한 파일이름
    private String storeFileName; //저장한 파일이름
    private char deleteYn; //삭제 여부

}
