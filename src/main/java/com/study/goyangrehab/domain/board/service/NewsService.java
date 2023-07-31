package com.study.goyangrehab.domain.board.service;

import com.study.goyangrehab.dto.BoardRequestDto;

import java.io.IOException;

public interface NewsService {

    void createNews(BoardRequestDto boardRequestDto) throws IOException;

    void updateNews(Long id, BoardRequestDto boardRequestDto) throws IOException;
}
