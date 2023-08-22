package com.study.goyangrehab.domain.board.controller;

import com.study.goyangrehab.common.dto.PageResponseDto;
import com.study.goyangrehab.domain.board.dto.BoardWithPageResponseDto;
import com.study.goyangrehab.domain.board.service.impl.NewsServiceImpl;
import com.study.goyangrehab.domain.board.dto.BoardAddForm;
import com.study.goyangrehab.domain.board.dto.BoardRequestDto;
import com.study.goyangrehab.domain.board.dto.BoardResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Tag(name = "boards", description = "게시판 API")
@Slf4j
@RestController
@RequestMapping("/api/board/news")
@RequiredArgsConstructor
public class NewsController {
    static final Logger logger = LogManager.getLogger(NewsController.class);
    private final NewsServiceImpl newsService;

    @GetMapping()
    public ResponseEntity<BoardWithPageResponseDto> getAllNewsWithPage(
            @NotBlank @RequestParam("page") int page
    ) {
        try {
            List<BoardResponseDto> boardResponseDtos = newsService.getNewsBoardList(page);
            PageResponseDto pageResponseDto = new PageResponseDto(newsService.getLastPageOfNews());
            return ResponseEntity.ok().body(new BoardWithPageResponseDto(boardResponseDtos, pageResponseDto));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping()
    public ResponseEntity<BoardResponseDto> createNews(
            @ModelAttribute BoardAddForm boardAddForm
    ) {
        BoardRequestDto boardRequestDto = boardAddForm.createBoardPostDto();
        try {
            newsService.createNews(boardRequestDto);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardResponseDto> updateNews(
            @PathVariable Long id,
            @ModelAttribute BoardAddForm boardAddForm
    ) {
        BoardRequestDto boardRequestDto = boardAddForm.createBoardPostDto();
        try {
            newsService.updateNews(id, boardRequestDto);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

}
