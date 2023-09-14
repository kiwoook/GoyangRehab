package com.study.goyangrehab.domain.board.controller;

import com.study.goyangrehab.common.dto.PageResponseDto;
import com.study.goyangrehab.domain.board.dto.BoardWithPageResponseDto;
import com.study.goyangrehab.domain.board.service.impl.NoticeServiceImpl;
import com.study.goyangrehab.domain.board.dto.BoardAddForm;
import com.study.goyangrehab.domain.board.dto.BoardRequestDto;
import com.study.goyangrehab.domain.board.dto.BoardResponseDto;
import com.study.goyangrehab.enums.NoticeCategory;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Tag(name = "boards", description = "게시판 API")
@Slf4j
@RestController
@RequestMapping("/api/board/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeServiceImpl noticeService;

    @GetMapping
    public ResponseEntity<BoardWithPageResponseDto> getAllNoticeWithPage(
            @NotBlank @RequestParam("page") int page
    ){
        try {
            List<BoardResponseDto> boardResponseDtos = noticeService.getNoticeBoardList(page);
            PageResponseDto pageResponseDto = new PageResponseDto(noticeService.getLastPageOfNotice());
            return ResponseEntity.ok().body(new BoardWithPageResponseDto(boardResponseDtos, pageResponseDto));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<BoardResponseDto> createNotice(
            @Valid @ModelAttribute BoardAddForm boardAddForm,
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
            @Valid @ModelAttribute BoardAddForm boardAddForm,
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
