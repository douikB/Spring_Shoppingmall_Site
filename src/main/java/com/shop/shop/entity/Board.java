package com.shop.shop.entity;

import com.shop.shop.dto.BoardFormDto;
import com.shop.shop.dto.ItemFormDto;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@Entity
@Table(name="board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity {

    @Id
    @Column(name="board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //PK

    @Column(length = 20)
    private String title; //제목
    private String content; //내용

    private int hits; //조회수
    
    private String writer; // 작성자

    @Column(nullable = true)
    private String uploadFileName;

    @Column(nullable = true)
    private String storeFileName;

    @Column(nullable = false)
    private char deleteYn; //삭제 여부

    @Builder
<<<<<<< HEAD
    public Board(String title, String content, int hits, char deleteYn, String uploadFileName, String storeFileName) {
=======
    public Board(String title, String content, int hits, char deleteYn, String fileName) {
>>>>>>> c1f45c957b60397af908407012f2ec8e3437083e
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.deleteYn = deleteYn;
<<<<<<< HEAD
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
=======
        this.uploadFileName = fileName;
        this.storeFileName = this.uploadFileName;
    }

    public void updateBoard(BoardFormDto boardFormDto){
        this.title = boardFormDto.getTitle();
        this.content = boardFormDto.getContent();
        this.uploadFileName = boardFormDto.getUploadFileName();

>>>>>>> c1f45c957b60397af908407012f2ec8e3437083e
    }

    public void removeBoard() {
        this.deleteYn = 'Y';
    }


}
