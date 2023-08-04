package com.study.goyangrehab.domain.board.service;

import com.study.goyangrehab.domain.board.entity.boards.Reply;
import com.study.goyangrehab.dto.BoardRequestDto;

import java.io.IOException;

public interface FreeService {

    void createFree(BoardRequestDto boardRequestDto) throws IOException;

    void updateFree(Long id, BoardRequestDto boardRequestDto) throws IOException;

    void addReplyToFree(Long id, Reply reply);
}
