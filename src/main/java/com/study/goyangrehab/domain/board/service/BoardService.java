package com.study.goyangrehab.domain.board.service;

import com.study.goyangrehab.domain.board.dto.BoardRequestDto;
import com.study.goyangrehab.domain.board.dto.BoardResponseDto;
import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.domain.file.entity.Attachment;
import com.study.goyangrehab.enums.BoardCategory;
import com.study.goyangrehab.enums.SearchType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.IOException;
import java.util.List;


public interface BoardService {

    BoardResponseDto getBoardById(Long id);

    void increaseViewCount(Long id);

    int getLastPageOfBoard();

    List<BoardResponseDto> getAllBoards();

    List<BoardResponseDto> search(int page, SearchType searchType, BoardCategory category, String query);

    Board createBoard(List<Attachment> attachments, BoardRequestDto boardRequestDto) throws UsernameNotFoundException;

    Board update(Long id, BoardRequestDto boardRequestDto) throws IOException;
    void deleteBoard(Long id);

    boolean isOwner(Authentication authentication, Long id);
}
