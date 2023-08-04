package com.study.goyangrehab.domain.board.service;

import com.study.goyangrehab.dto.BoardRequestDto;

import java.io.IOException;
import java.time.LocalDate;

public interface EventService {

    void createEvent(BoardRequestDto boardRequestDto, LocalDate date) throws IOException;

    void updateEvent(Long id, BoardRequestDto boardRequestDto, LocalDate date) throws IOException;
}
