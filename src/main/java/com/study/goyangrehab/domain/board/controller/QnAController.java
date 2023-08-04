package com.study.goyangrehab.domain.board.controller;

import com.study.goyangrehab.domain.board.service.impl.QnAServiceImpl;
import com.study.goyangrehab.dto.BoardAddForm;
import com.study.goyangrehab.dto.BoardRequestDto;
import com.study.goyangrehab.dto.BoardResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class QnAController {
    static final Logger logger = LogManager.getLogger(QnAController.class);
    private final QnAServiceImpl qnaService;

    @GetMapping("/qna")
    public ResponseEntity<List<BoardResponseDto>> getAllQnAWithPage(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size
    ){
        return null;
    }


    @PostMapping("/qna")

    public ResponseEntity<BoardResponseDto> createQnA(
            @ModelAttribute BoardAddForm boardAddForm
    ) {
        BoardRequestDto boardRequestDto = boardAddForm.createBoardPostDto();
        try {
            qnaService.createQnA(boardRequestDto);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/qna/{id}")
    public ResponseEntity<BoardResponseDto> updateQnA(
            @PathVariable Long id,
            @ModelAttribute BoardAddForm boardAddForm
    ) {
        BoardRequestDto boardRequestDto = boardAddForm.createBoardPostDto();
        try {
            qnaService.updateQnA(id, boardRequestDto);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/qna/{id}")
    public ResponseEntity<BoardResponseDto> replyToQnA(
            @PathVariable Long id,
            @ModelAttribute BoardAddForm boardAddForm
    ) {
        BoardRequestDto boardRequestDto = boardAddForm.createBoardPostDto();
        try {
            qnaService.addReplyToQnA(id, boardRequestDto);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}