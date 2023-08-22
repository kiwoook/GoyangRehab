package com.study.goyangrehab.domain.board.service;

import com.study.goyangrehab.domain.board.dto.BoardRequestDto;
import com.study.goyangrehab.domain.board.dto.BoardResponseDto;
import com.study.goyangrehab.enums.BoardCategory;
import com.study.goyangrehab.enums.SearchType;

import java.io.IOException;
import java.util.List;


public interface BoardService {

    BoardResponseDto getBoardById(Long id);

    void increaseViewCount(Long id);

    int getLastPageOfBoard();

    List<BoardResponseDto> getAllBoards();

    List<BoardResponseDto> search(int page, SearchType searchType, BoardCategory category, String query);

    void createBoard(BoardRequestDto boardRequestDto) throws IOException;


    void deleteBoard(Long id);


}
