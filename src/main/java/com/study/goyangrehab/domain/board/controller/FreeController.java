package com.study.goyangrehab.domain.board.controller;

import com.study.goyangrehab.domain.board.service.impl.FreeServiceImpl;
import com.study.goyangrehab.domain.board.service.impl.JobPostingServiceImpl;
import com.study.goyangrehab.dto.BoardAddForm;
import com.study.goyangrehab.dto.BoardRequestDto;
import com.study.goyangrehab.dto.BoardResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Tag(name = "boards", description = "게시판 API")
@Log4j2
@RestController
@RequestMapping("/api/board/free")
@RequiredArgsConstructor
public class FreeController {

    static final Logger logger = LogManager.getLogger(FreeController.class);
    private final FreeServiceImpl freeService;

    @GetMapping()
    public ResponseEntity<List<BoardResponseDto>> getAllFreeBoardWithPage(
            @NotBlank @RequestParam("page") int page
    ) {
        try {
            List<BoardResponseDto> boardResponseDtos = freeService.getFreeBoardList(page);
            return ResponseEntity.ok().body(boardResponseDtos);
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping()
    public ResponseEntity<BoardResponseDto> createFree(
            @ModelAttribute BoardAddForm boardAddForm
    ) {
        BoardRequestDto boardRequestDto = boardAddForm.createBoardPostDto();
        try {
            freeService.createFree(boardRequestDto);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardResponseDto> updateFree(
            @PathVariable Long id,
            @ModelAttribute BoardAddForm boardAddForm
    ) {
        BoardRequestDto boardRequestDto = boardAddForm.createBoardPostDto();
        try {
            freeService.updateFree(id, boardRequestDto);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
