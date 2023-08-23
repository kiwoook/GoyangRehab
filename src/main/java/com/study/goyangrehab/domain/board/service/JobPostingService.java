package com.study.goyangrehab.domain.board.service;

import com.study.goyangrehab.domain.board.dto.BoardRequestDto;
import com.study.goyangrehab.domain.board.dto.BoardResponseDto;

import java.io.IOException;
import java.util.List;

public interface JobPostingService {

    List<BoardResponseDto> getJobPostingList(int page);

    void createJobPosting(BoardRequestDto boardRequestDto) throws IOException;

    void updateJobPosting(Long id, BoardRequestDto boardRequestDto) throws IOException;

    int getLastPageOfJobPosting();

    void addReplyToJobPosting(Long id, BoardRequestDto boardRequestDto) throws IOException;

}
