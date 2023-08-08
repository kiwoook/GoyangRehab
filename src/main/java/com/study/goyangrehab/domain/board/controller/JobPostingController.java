package com.study.goyangrehab.domain.board.controller;

import com.study.goyangrehab.domain.board.service.impl.JobPostingServiceImpl;
import com.study.goyangrehab.domain.board.service.impl.QnAServiceImpl;
import com.study.goyangrehab.dto.BoardAddForm;
import com.study.goyangrehab.dto.BoardRequestDto;
import com.study.goyangrehab.dto.BoardResponseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/board/job_posting")
@RequiredArgsConstructor
public class JobPostingController {
    static final Logger logger = LogManager.getLogger(JobPostingController.class);
    private final JobPostingServiceImpl jobPostingService;

    @GetMapping()
    public ResponseEntity<List<BoardResponseDto>> getAllJobPostingWithPage(
            @NotBlank @RequestParam("page") Integer page
    ){
        try{
            List<BoardResponseDto> boardResponseDtos = jobPostingService.getJobPostingList(page);
            return ResponseEntity.ok().body(boardResponseDtos);
        }catch (NullPointerException e){
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping()
    public ResponseEntity<BoardResponseDto> createJobPosting(
            @ModelAttribute BoardAddForm boardAddForm
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
            @ModelAttribute BoardAddForm boardAddForm
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
            @ModelAttribute BoardAddForm boardAddForm
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
