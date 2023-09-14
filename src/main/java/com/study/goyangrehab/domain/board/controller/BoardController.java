package com.study.goyangrehab.domain.board.controller;

import com.study.goyangrehab.domain.board.dto.BoardAddForm;
import com.study.goyangrehab.domain.board.dto.BoardResponseDto;
import com.study.goyangrehab.domain.board.service.impl.BoardServiceImpl;
import com.study.goyangrehab.enums.BoardCategory;
import com.study.goyangrehab.enums.SearchType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "boards", description = "게시판 API")
@Slf4j
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    static final Logger logger = LogManager.getLogger(BoardController.class);
    private static final String VIEW_COOKIE = "goyang_rehab_viewed_";
    private final BoardServiceImpl boardService;

    // READ

    @Operation(summary = "게시글 ID 조회", description = "파라미터 id 값을 이용해 BoardResponseDto를 반환합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        boolean isViewed = false;

        if (cookies != null) {
            logger.info("쿠키 탐색 시작");
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(VIEW_COOKIE + id)) {
                    isViewed = true;
                    break;
                }
            }
        }

        try {
            if (!isViewed) {
                logger.info("쿠키 생성");
                boardService.increaseViewCount(id);
                Cookie cookie = new Cookie(VIEW_COOKIE + id, "true");
                cookie.setMaxAge(60 * 60);
                response.addCookie(cookie);
            }
            BoardResponseDto boardResponseDto = boardService.getBoardById(id);
            return ResponseEntity.ok().body(boardResponseDto);
        } catch (EntityNotFoundException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    // DELETE
    @PreAuthorize("hasRole('ADMIN') or @boardServiceImpl.isOwner(authentication, #id)")
    @Operation(summary = "게시글 ID 삭제", description = "파라미터 id 값을 활용해 게시글을 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<BoardResponseDto> deleteBoard(
            @PathVariable Long id
    ) {
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
            @NotBlank @RequestParam(name = "page") int page,
            @NonNull @RequestParam(name = "category") BoardCategory category,
            @NonNull @RequestParam(name = "type") SearchType searchType,
            @NotBlank @RequestParam(name = "query") String query
    ) {
        try {
            List<BoardResponseDto> boardResponseDtoList = boardService.search(page, searchType, category, query);
            return ResponseEntity.ok().body(boardResponseDtoList);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
