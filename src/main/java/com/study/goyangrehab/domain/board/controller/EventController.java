package com.study.goyangrehab.domain.board.controller;

import com.study.goyangrehab.domain.board.service.impl.EventServiceImpl;
import com.study.goyangrehab.dto.BoardAddForm;
import com.study.goyangrehab.dto.BoardRequestDto;
import com.study.goyangrehab.dto.BoardResponseDto;
import com.study.goyangrehab.dto.EventResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/board/event")
@RequiredArgsConstructor
public class EventController {
    static final Logger logger = LogManager.getLogger(EventController.class);
    private final EventServiceImpl eventService;

    @GetMapping
    public ResponseEntity<List<EventResponseDto>> getEventsByMonthAndYear(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth yearMonth
    ) {
        logger.info("getEventsByMonthAndYear 실행");
        List<EventResponseDto> eventResponseDtos = eventService.getEventsByMonthAndYear(yearMonth);
        return ResponseEntity.ok().body(eventResponseDtos);
    }

    @PostMapping()
    public ResponseEntity<BoardResponseDto> createEvent(
            @ModelAttribute BoardAddForm boardAddForm,
            @RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate date
    ) {
        BoardRequestDto boardRequestDto = boardAddForm.createBoardPostDto();
        try {
            eventService.createEvent(boardRequestDto, date);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardResponseDto> updateEvent(
            @PathVariable Long id,
            @ModelAttribute BoardAddForm boardAddForm,
            @RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate date
    ) {
        BoardRequestDto boardRequestDto = boardAddForm.createBoardPostDto();
        try {
            eventService.updateEvent(id, boardRequestDto, date);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
