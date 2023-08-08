package com.study.goyangrehab.domain.board.controller;

import com.study.goyangrehab.domain.board.service.impl.NoticeServiceImpl;
import com.study.goyangrehab.dto.BoardAddForm;
import com.study.goyangrehab.dto.BoardRequestDto;
import com.study.goyangrehab.dto.BoardResponseDto;
import com.study.goyangrehab.enums.NoticeCategory;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/board/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeServiceImpl noticeService;

    @PostMapping
    public ResponseEntity<BoardResponseDto> createNotice(
            @ModelAttribute BoardAddForm boardAddForm,
            @RequestBody NoticeCategory category

    ) {
        BoardRequestDto boardRequestDto = boardAddForm.createBoardPostDto();

        try {
            noticeService.createNotice(boardRequestDto, category);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardResponseDto> updateNotice(
            @PathVariable Long id,
            @ModelAttribute BoardAddForm boardAddForm,
            @RequestBody NoticeCategory category
    ) {
        BoardRequestDto boardRequestDto = boardAddForm.createBoardPostDto();

        try {

            noticeService.updateNotice(id, boardRequestDto, category);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException | IOException e) {
            return ResponseEntity.badRequest().build();
        }


    }
}
