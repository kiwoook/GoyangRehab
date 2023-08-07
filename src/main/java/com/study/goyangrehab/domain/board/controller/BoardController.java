package com.study.goyangrehab.domain.board.controller;

import com.study.goyangrehab.dto.BoardAddForm;
import com.study.goyangrehab.dto.BoardResponseDto;
import com.study.goyangrehab.service.impl.BoardServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    static final Logger logger = LogManager.getLogger(BoardController.class);
    private final BoardServiceImpl boardService;

    @PostMapping
    public ResponseEntity<BoardResponseDto> createBoard(@ModelAttribute BoardAddForm boardAddForm) {
        return null;
    }

    // READ
    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable Long id) {
        try {
            BoardResponseDto boardResponseDto = boardService.getBoardById(id);
            return ResponseEntity.ok().body(boardResponseDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<BoardResponseDto> deleteBoard(
            @PathVariable Long id
    ) {
        // 제작자가 맞는지 탐색해야함. user 테이블과 연결시켜야함.
        try {
            boardService.deleteBoard(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
