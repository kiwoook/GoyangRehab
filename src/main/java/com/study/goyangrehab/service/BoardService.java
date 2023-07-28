package com.study.goyangrehab.service;

import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.domain.board.entity.boards.Reply;
import com.study.goyangrehab.dto.BoardRequestDto;

import java.io.IOException;
import java.util.List;


public interface BoardService {

    Board getBoardById(Long id);

    List<Board> getAllBoards();

    List<Board> getBoardsByAuthor(String author);

    void createBoard(BoardRequestDto boardRequestDto) throws IOException;

    void updateBoard(BoardRequestDto boardRequestDto);

    void deleteBoard(Long id);

    void addReplyToBoard(Long boardId, Reply reply);

}
