package com.study.goyangrehab.service;

import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.domain.board.entity.boards.Reply;
import com.study.goyangrehab.dto.BoardRequestDto;
import com.study.goyangrehab.dto.BoardResponseDto;

import java.io.IOException;
import java.util.List;


public interface BoardService {

    BoardResponseDto getBoardById(Long id);

    List<BoardResponseDto> getAllBoards();

    List<BoardResponseDto> getBoardsByCreator(String creator);

    List<BoardResponseDto> getBoardsByTitle(String title);

    List<BoardResponseDto> getBoardsByContent(String content);

    List<BoardResponseDto> getBoardsByUserId(String userId);

    List<BoardResponseDto> getBoardsByTitleOrContent(String query);

    void createBoard(BoardRequestDto boardRequestDto) throws IOException;

    void updateBoard(Long id, BoardRequestDto boardRequestDto);

    void deleteBoard(Long id);

    void addReplyToBoard(Long boardId, Reply reply);

}
