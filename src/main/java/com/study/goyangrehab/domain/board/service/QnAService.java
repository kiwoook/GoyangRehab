package com.study.goyangrehab.domain.board.service;

import com.study.goyangrehab.domain.board.entity.boards.QnA;
import com.study.goyangrehab.domain.board.dto.BoardRequestDto;
import com.study.goyangrehab.domain.board.dto.BoardResponseDto;

import java.io.IOException;
import java.util.List;

public interface QnAService {

    List<BoardResponseDto> getQnABoardList(Integer page);

    int getLastPageOfQnA();

    QnA createQnA(BoardRequestDto boardRequestDto) throws IOException;

    QnA updateQnA(Long id, BoardRequestDto boardRequestDto) throws IOException;

    void addReplyToQnA(Long id, BoardRequestDto boardRequestDto) throws IOException;


}
