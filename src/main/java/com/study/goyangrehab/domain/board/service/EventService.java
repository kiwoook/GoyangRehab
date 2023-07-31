package com.study.goyangrehab.domain.board.service;

import com.study.goyangrehab.dto.BoardRequestDto;

import java.time.LocalDate;

public interface EventService {

    void createEvent(BoardRequestDto boardRequestDto, LocalDate date);

    void updateEvent(Long id, BoardRequestDto boardRequestDto, LocalDate date);
}
