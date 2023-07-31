package com.study.goyangrehab.domain.board.service;

import com.study.goyangrehab.dto.BoardRequestDto;

import java.io.IOException;

public interface JobPostingService {

    void createJobPosting(BoardRequestDto boardRequestDto) throws IOException;

    void updateJobPosting(Long id, BoardRequestDto boardRequestDto);
}
