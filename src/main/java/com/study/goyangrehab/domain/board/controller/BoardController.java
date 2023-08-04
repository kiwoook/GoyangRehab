package com.study.goyangrehab.domain.board.controller;

import com.study.goyangrehab.domain.board.service.impl.*;
import com.study.goyangrehab.dto.BoardAddForm;
import com.study.goyangrehab.dto.BoardRequestDto;
import com.study.goyangrehab.dto.BoardResponseDto;
import com.study.goyangrehab.enums.BoardCategory;
import com.study.goyangrehab.enums.NoticeCategory;
import com.study.goyangrehab.service.impl.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    static final Logger logger = LogManager.getLogger(BoardController.class);
    private final EventServiceImpl eventService;
    private final JobPostingServiceImpl jobPostingService;
    private final NewsServiceImpl newsService;
    private final NoticeServiceImpl noticeService;
    private final QnAServiceImpl qnaService;
    private final BoardServiceImpl boardService;

    @PostMapping
    public ResponseEntity<BoardResponseDto> createBoard(@ModelAttribute BoardAddForm boardAddForm){
        return null;
    }

    // READ
    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable Long id){
        try{
            BoardResponseDto boardResponseDto = boardService.getBoardById(id);
            return ResponseEntity.ok().body(boardResponseDto);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().build();
        }
    }



//    CREATE
    @PostMapping("/event")
    public ResponseEntity<BoardResponseDto> createEvent(
            @ModelAttribute BoardAddForm boardAddForm,
            @RequestParam(value="date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
            ){
        BoardRequestDto boardRequestDto = boardAddForm.createBoardPostDto();
        try{
            eventService.createEvent(boardRequestDto, date);
            return ResponseEntity.ok().build();
        }catch(IOException e){
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/jobposting")
    public ResponseEntity<BoardResponseDto> createJobPosting(
            @ModelAttribute BoardAddForm boardAddForm
    ){
        BoardRequestDto boardRequestDto = boardAddForm.createBoardPostDto();
        try{
            jobPostingService.createJobPosting(boardRequestDto);
            return ResponseEntity.ok().build();
        }catch(IOException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/news")
    public ResponseEntity<BoardResponseDto> createNews(
            @ModelAttribute BoardAddForm boardAddForm
    ){
        BoardRequestDto boardRequestDto = boardAddForm.createBoardPostDto();
        try{
            newsService.createNews(boardRequestDto);
            return ResponseEntity.ok().build();
        }catch(IOException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/notice")
    public ResponseEntity<BoardResponseDto> createNotice(
            @ModelAttribute BoardAddForm boardAddForm,
            @RequestParam(value="category") NoticeCategory category
            ){
        BoardRequestDto boardRequestDto = boardAddForm.createBoardPostDto();
        try{
            noticeService.createNotice(boardRequestDto, category);
            return ResponseEntity.ok().build();
        }catch(IOException e){
            return ResponseEntity.badRequest().build();
        }
    }




//  reply


}
