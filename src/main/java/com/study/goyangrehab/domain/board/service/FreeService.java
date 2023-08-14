package com.study.goyangrehab.domain.board.service;

import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.domain.board.entity.boards.Free;
import com.study.goyangrehab.dto.BoardRequestDto;
import com.study.goyangrehab.dto.BoardResponseDto;

import java.io.IOException;
import java.util.List;

public interface FreeService {

    List<BoardResponseDto> getFreeBoardList(int page);

    Free createFree(BoardRequestDto boardRequestDto) throws IOException;

    Free updateFree(Long id, BoardRequestDto boardRequestDto) throws IOException;

    Board addReplyToFree(Long id, BoardRequestDto boardRequestDto) throws IOException;
}
