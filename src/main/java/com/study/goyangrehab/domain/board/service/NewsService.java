package com.study.goyangrehab.domain.board.service;

import com.study.goyangrehab.domain.board.entity.boards.News;
import com.study.goyangrehab.domain.board.dto.BoardRequestDto;
import com.study.goyangrehab.domain.board.dto.BoardResponseDto;

import java.io.IOException;
import java.util.List;

public interface NewsService {

    List<BoardResponseDto> getNewsBoardList(int page);

    int getLastPageOfNews();

    News createNews(BoardRequestDto boardRequestDto) throws IOException;

    News updateNews(Long id, BoardRequestDto boardRequestDto) throws IOException;
}
