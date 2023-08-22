package com.study.goyangrehab.domain.board.controller;

import com.study.goyangrehab.common.dto.PageResponseDto;
import com.study.goyangrehab.domain.board.dto.BoardWithPageResponseDto;
import com.study.goyangrehab.domain.board.service.impl.QnAServiceImpl;
import com.study.goyangrehab.domain.board.dto.BoardAddForm;
import com.study.goyangrehab.domain.board.dto.BoardRequestDto;
import com.study.goyangrehab.domain.board.dto.BoardResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Tag(name = "boards", description = "게시판 API")
@Log4j2
@RestController
@RequestMapping("/api/board/qna")
@RequiredArgsConstructor
public class QnAController {
    static final Logger logger = LogManager.getLogger(QnAController.class);
    private final QnAServiceImpl qnaService;

    @GetMapping()
    public ResponseEntity<BoardWithPageResponseDto> getAllQnAWithPage(
            @NotBlank @RequestParam("page") Integer page
    ) {
        try {
            List<BoardResponseDto> boardResponseDtos = qnaService.getQnABoardList(page);
            PageResponseDto pageResponseDto = new PageResponseDto(qnaService.getLastPageOfQnA());
            return ResponseEntity.ok().body(new BoardWithPageResponseDto(boardResponseDtos, pageResponseDto));
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping()
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

    @PutMapping("/{id}")
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
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PostMapping("/{id}")
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
