package com.study.goyangrehab.domain.board.controller;

import com.study.goyangrehab.dto.BoardAddForm;
import com.study.goyangrehab.dto.BoardResponseDto;
import com.study.goyangrehab.enums.BoardCategory;
import com.study.goyangrehab.enums.SearchType;
import com.study.goyangrehab.service.impl.BoardServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotBlank;
import jdk.jfr.Category;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "boards", description = "게시판 API")
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

    @Operation(summary = "게시글 ID 조회", description = "파라미터 id 값을 이용해 BoardResponseDto를 반환합니다.")
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
    @Operation(summary = "게시글 ID 삭제", description = "파라미터 id 값을 활용해 게시글을 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<BoardResponseDto> deleteBoard(
            @PathVariable Long id
    ) {
        // TODO 제작자가 맞는지 탐색해야함. user 테이블과 연결시켜야함.

        try {
            boardService.deleteBoard(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary = "게시물 검색 조회", description = "파라미터 BoardType, SearchType, Query를 받아 해당 Board들을 반환합니다.")
    @GetMapping("/search")
    public ResponseEntity<List<BoardResponseDto>> search(
            @NonNull @RequestParam(name = "category") BoardCategory category,
            @NonNull @RequestParam(name = "search")SearchType searchType,
            @NotBlank @RequestParam(name = "query") String query
            ){
        // TODO 게시물 검색 기능 구현
        try{
            return null;
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }


}
