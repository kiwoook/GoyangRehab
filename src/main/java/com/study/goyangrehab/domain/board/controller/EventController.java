package com.study.goyangrehab.domain.board.controller;

import com.study.goyangrehab.domain.board.service.impl.EventServiceImpl;
import com.study.goyangrehab.domain.board.dto.BoardAddForm;
import com.study.goyangrehab.domain.board.dto.BoardRequestDto;
import com.study.goyangrehab.domain.board.dto.BoardResponseDto;
import com.study.goyangrehab.domain.board.dto.EventResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Tag(name = "boards", description = "게시판 API")
@Log4j2
@RestController
@RequestMapping("/api/board/event")
@RequiredArgsConstructor
public class EventController {
    static final Logger logger = LogManager.getLogger(EventController.class);
    private final EventServiceImpl eventService;

    @GetMapping
    public ResponseEntity<List<EventResponseDto>> getEventsByMonthAndYear(
            @Schema(name = "년-월", example = "yyyy-MM")
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
