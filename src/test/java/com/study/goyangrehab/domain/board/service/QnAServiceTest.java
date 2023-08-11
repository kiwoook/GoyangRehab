package com.study.goyangrehab.domain.board.service;

import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.domain.board.entity.boards.QnA;
import com.study.goyangrehab.domain.board.entity.boards.Reply;
import com.study.goyangrehab.domain.board.repository.BoardRepository;
import com.study.goyangrehab.domain.board.repository.QnARepository;
import com.study.goyangrehab.domain.board.repository.ReplyRepository;
import com.study.goyangrehab.domain.board.service.impl.QnAServiceImpl;
import com.study.goyangrehab.dto.BoardRequestDto;
import com.study.goyangrehab.service.impl.AttachmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QnAServiceTest {

    Board board1 = Board.builder()
            .title("제목1")
            .content("내용")
            .creator("글쓴이")
            .build();
    Board board2 = Board.builder()
            .title("수정1")
            .content("수정 내용")
            .creator("수정 글쓴이")
            .build();
    Board board3 = Board.builder()
            .title("답글1")
            .content("답글1 내용")
            .creator("답글1 글쓴이")
            .build();
    @Mock
    private BoardRepository boardRepository;
    @Mock
    private QnARepository qnARepository;
    @Mock
    private ReplyRepository replyRepository;
    private QnAServiceImpl qnAService;
    @Mock
    private AttachmentServiceImpl attachmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        qnAService = new QnAServiceImpl(attachmentService, boardRepository, qnARepository, replyRepository);
    }


    @Test
    @DisplayName("QnA 생성 테스트")
    void testCreateQnA() throws IOException {
        // Mocking repository response
        QnA qna = new QnA(board1);
        when(qnARepository.save(any(QnA.class))).thenReturn(qna);

        // Calling the service method
        BoardRequestDto requestDto = new BoardRequestDto(board1.getTitle(), board1.getContent(), board1.getCreator(), new ConcurrentHashMap<>());
        qnAService.createQnA(requestDto);

        // Verify that the repository method was called with correct arguments
        verify(qnARepository).save(any(QnA.class));
    }

    @Test
    @DisplayName("QnA 업데이트 테스트")
    void testUpdateQnA() throws IOException {
        // Mocking repository response
        QnA qna = new QnA(board1);
        when(qnARepository.findById(anyLong())).thenReturn(java.util.Optional.of(qna));

        // Calling the service method
        BoardRequestDto requestDto = new BoardRequestDto(board2.getTitle(), board2.getContent(), board2.getCreator(), new ConcurrentHashMap<>());
        qnAService.updateQnA(1L, requestDto);

        // Verify that the repository method was called with correct arguments
        verify(qnARepository).findById(anyLong());
        verify(qnARepository).save(any(QnA.class));
    }

    @Test
    @DisplayName("reply 생성 to QnA 테스트")
    void testAddReplyToQnA() throws IOException {
        // Mocking repository response
        QnA qna = new QnA(board1);
        Reply reply = new Reply(board3);
        when(qnARepository.findById(anyLong())).thenReturn(java.util.Optional.of(qna));

        // Calling the service method
        BoardRequestDto requestDto = new BoardRequestDto(board3.getTitle(), board3.getContent(), board3.getCreator(), new ConcurrentHashMap<>());
        qnAService.addReplyToQnA(1L, requestDto);

        // Verify that the repository methods were called with correct arguments
        verify(qnARepository).findById(anyLong());
        verify(boardRepository).save(any(Reply.class));
        verify(qnARepository).save(any(QnA.class));
    }

    // Add more test methods for other service functionalities

}