package com.study.goyangrehab.domain.board.repository;

import com.study.goyangrehab.config.TestConfig;
import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.domain.board.entity.boards.Event;
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
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfig.class)
class BoardRepositoryTest {
    @PersistenceContext
    EntityManager entityManager;
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
    @Autowired
    private BoardRepository boardRepository;

    @Test
    void saveTest() {
        // given
        Board board1 = Board.builder()
                .title("제목")
                .content("내용")
                .creator("글쓴이")
                .build();

        Board board2 = Board.builder()
                .title("RE:답글1")
                .content("답글 내용")
                .creator("답글 글쓴이")
                .build();

        QnA qna1 = new QnA(board1);

        Reply reply1 = new Reply(board2);

        // when

        QnA savedQnA = boardRepository.save(qna1);
        Reply savedReply = boardRepository.save(reply1);

        // then
        assertThat(savedQnA).isEqualTo(qna1);
        assertThat(savedReply).isEqualTo(reply1);

    }

    @Test
    @DisplayName("QnA&Reply findAll 테스트")
    void queryDslTest() {
        // given


        QnA qna1 = new QnA(board1);
        QnA qna2 = new QnA(board3);

        Reply reply1 = new Reply(board2);

        List<Board> expectedResult = new ArrayList<>();

        expectedResult.add(board3);
        expectedResult.add(board1);
        expectedResult.add(reply1);


        // when
        boardRepository.save(reply1);

        reply1.addReply(qna1);
        boardRepository.save(qna1);
        boardRepository.save(qna2);

        List<Board> results = boardRepository.findQnAWithReply(PageRequest.of(0, 10));
        // then

        assertThat(results).hasSize(expectedResult.size());

        for (int i = 0; i < results.size(); i++) {
            assertThat(results.get(i).getTitle()).isEqualTo(expectedResult.get(i).getTitle());
        }

    }

    @Test
    @DisplayName("이벤트 기간 조회 테스트")
    void getEventsTest() {
        //given

        LocalDate dateTime1 = LocalDate.of(2023, 8, 1);
        LocalDate dateTime2 = LocalDate.of(2023, 8, 15);
        LocalDate dateTime3 = LocalDate.of(2023, 9, 5);

        Event event1 = new Event(board1, dateTime1);
        Event event2 = new Event(board2, dateTime2);
        Event event3 = new Event(board3, dateTime3);

        boardRepository.saveAll(List.of(event1, event2, event3));

        //when
        YearMonth yearMonth = YearMonth.of(2023, 9);

        List<Event> result = boardRepository.findEventsForMonthAndYear(yearMonth);

        assertThat(result.get(0).getTitle()).isEqualTo(board3.getTitle());

    }
}
