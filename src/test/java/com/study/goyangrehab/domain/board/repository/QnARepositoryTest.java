package com.study.goyangrehab.domain.board.repository;

import com.study.goyangrehab.config.TestConfig;
import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.domain.board.entity.boards.QnA;
import com.study.goyangrehab.domain.board.entity.boards.Reply;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfig.class)
class QnARepositoryTest {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private QnARepository qnaRepository;

    @Autowired
    private BoardRepository boardRepository;


    @Test
    void saveTest(){
        // given
        Board board1 = Board.builder()
                .title("제목")
                .content("내용")
                .creator("글쓴이")
                .build();

        Board board2 = Board.builder()
                .title("답글1")
                .content("답글 내용")
                .creator("답글 글쓴이")
                .build();

        QnA qna1 = new QnA(board1);

        Reply reply1 = new Reply(board2);

        // when

        boardRepository.save(qna1);
        boardRepository.save(reply1);

        // then


    }
    @Test
    @DisplayName("QnA&Reply findAll 테스트")
    void queryDslTest(){
        // given
        Board board1 = Board.builder()
                .title("제목1")
                .content("내용")
                .creator("글쓴이")
                .build();

        Board board2 = Board.builder()
                .title("답글1")
                .content("답글 내용")
                .creator("답글 글쓴이")
                .build();

        Board board3 = Board.builder()
                .title("제목2")
                .content("내용2")
                .creator("글쓴이2")
                .build();

        QnA qna1 = new QnA(board1);
        QnA qna2 = new QnA(board3);

        Reply reply1 = new Reply(board2);

        List<Board> expectedResult = new ArrayList<>();

        expectedResult.add(board1);
        expectedResult.add(board2);
        expectedResult.add(board3);


        // when
        boardRepository.save(reply1);

        qna1.addReply(reply1);
        boardRepository.save(qna1);
        boardRepository.save(qna2);

        List<QnA> results = qnaRepository.findAll();



        // then

        assertThat(results).isEqualTo(expectedResult);


    }
}
