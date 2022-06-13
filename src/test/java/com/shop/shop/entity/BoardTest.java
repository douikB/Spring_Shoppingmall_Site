package com.shop.shop.entity;

import com.shop.shop.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class BoardTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BoardRepository boardRepository;

    @Test
    @DisplayName("보드 생성 테스트")
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void boardCreateTest() throws Exception {
        Board board = Board.builder()
                .title("1번 게시글 제목")
                .content("1번 게시글 내용")
                .hits(0)
                .deleteYn('N')
                .build();

        boardRepository.save(board);

        Board entity = boardRepository.findById((long) 1).get();

        System.out.println(entity);

        assertThat(entity.getTitle()).isEqualTo(board.getTitle());
        assertThat(entity.getContent()).isEqualTo(board.getContent());
    }

    @Test
    @DisplayName("보드 생성 테스트")
    public void findAll() {
        long boardsCount = boardRepository.count();

        List<Board> boards = boardRepository.findAll();
    }

    @Test
    @DisplayName("보드 생성 테스트")
    public void deleteBoard() {
        Board entity = boardRepository.findById((long) 1).get();

        boardRepository.delete(entity);


    }

}