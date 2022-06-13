package com.shop.shop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardListDto {

    private Long id; //PK

    private String title; //제목
    private String content; //내용
    private int hits; //조회수
    private char deleteYn; //삭제 여부
}
