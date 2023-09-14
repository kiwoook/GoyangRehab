package com.study.goyangrehab.domain.board.controller;

import com.study.goyangrehab.common.dto.PageResponseDto;
import com.study.goyangrehab.domain.board.dto.BoardWithPageResponseDto;
import com.study.goyangrehab.domain.board.service.impl.JobPostingServiceImpl;
import com.study.goyangrehab.domain.board.dto.BoardAddForm;
import com.study.goyangrehab.domain.board.dto.BoardRequestDto;
import com.study.goyangrehab.domain.board.dto.BoardResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
@RequestMapping("/api/board/job_posting")
@RequiredArgsConstructor
public class JobPostingController {
    static final Logger logger = LogManager.getLogger(JobPostingController.class);
    private final JobPostingServiceImpl jobPostingService;

    @GetMapping()
    public ResponseEntity<BoardWithPageResponseDto> getAllJobPostingWithPage(
            @NotBlank @RequestParam("page") Integer page
    ) {
        try {
            List<BoardResponseDto> boardResponseDtos = jobPostingService.getJobPostingList(page);
            PageResponseDto pageResponseDto = new PageResponseDto(jobPostingService.getLastPageOfJobPosting());
            return ResponseEntity.ok().body(new BoardWithPageResponseDto(boardResponseDtos, pageResponseDto));
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping()
    public ResponseEntity<BoardResponseDto> createJobPosting(
            @Valid @ModelAttribute BoardAddForm boardAddForm
    ) {
        BoardRequestDto boardRequestDto = boardAddForm.createBoardPostDto();
        try {
            jobPostingService.createJobPosting(boardRequestDto);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardResponseDto> updateJobPosting(
            @PathVariable Long id,
            @Valid @ModelAttribute BoardAddForm boardAddForm
    ) {
        BoardRequestDto boardRequestDto = boardAddForm.createBoardPostDto();
        try {
            jobPostingService.updateJobPosting(id, boardRequestDto);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<BoardResponseDto> replyToJobPosting(
            @PathVariable Long id,
            @Valid @ModelAttribute BoardAddForm boardAddForm
    ) {
        BoardRequestDto boardRequestDto = boardAddForm.createBoardPostDto();
        try {
            jobPostingService.addReplyToJobPosting(id, boardRequestDto);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
