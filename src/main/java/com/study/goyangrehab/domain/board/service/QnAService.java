package com.study.goyangrehab.domain.board.service;

import com.study.goyangrehab.domain.board.entity.boards.Reply;
import com.study.goyangrehab.dto.BoardRequestDto;

import java.io.IOException;

public interface QnAService {

    void createQnA(BoardRequestDto boardRequestDto) throws IOException;

    void updateQnA(Long id, BoardRequestDto boardRequestDto) throws IOException;

    void addReplyToBoard(Long id, Reply reply);


}
