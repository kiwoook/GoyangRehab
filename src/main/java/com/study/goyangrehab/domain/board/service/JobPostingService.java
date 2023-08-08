package com.study.goyangrehab.domain.board.service;

import com.study.goyangrehab.dto.BoardRequestDto;
import com.study.goyangrehab.dto.BoardResponseDto;

import java.io.IOException;
import java.util.List;

public interface JobPostingService {

    List<BoardResponseDto> getJobPostingList(Integer page);

    void createJobPosting(BoardRequestDto boardRequestDto) throws IOException;

    void updateJobPosting(Long id, BoardRequestDto boardRequestDto) throws IOException;

    void addReplyToJobPosting(Long id, BoardRequestDto boardRequestDto) throws IOException;
}
