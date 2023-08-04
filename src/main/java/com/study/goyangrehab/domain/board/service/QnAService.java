package com.study.goyangrehab.domain.board.service;

import com.study.goyangrehab.dto.BoardRequestDto;

import java.io.IOException;

public interface QnAService {

    void createQnA(BoardRequestDto boardRequestDto) throws IOException;

    void updateQnA(Long id, BoardRequestDto boardRequestDto) throws IOException;

    void addReplyToQnA(Long id, BoardRequestDto boardRequestDto) throws IOException;


}
