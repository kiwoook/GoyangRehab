package com.study.goyangrehab.domain.board.service;

import com.study.goyangrehab.domain.board.dto.BoardRequestDto;
import com.study.goyangrehab.domain.board.dto.EventResponseDto;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public interface EventService {

    List<EventResponseDto> getEventsByMonthAndYear(YearMonth yearMonth);

    void createEvent(BoardRequestDto boardRequestDto, LocalDate date) throws IOException;

    int getLastPageOfEvent();

    void updateEvent(Long id, BoardRequestDto boardRequestDto, LocalDate date) throws IOException;
}
