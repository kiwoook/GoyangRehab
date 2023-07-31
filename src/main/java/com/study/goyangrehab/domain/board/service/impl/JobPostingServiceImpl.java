package com.study.goyangrehab.domain.board.service.impl;

import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.domain.board.entity.boards.JobPosting;
import com.study.goyangrehab.domain.board.entity.boards.News;
import com.study.goyangrehab.domain.board.repository.BoardRepository;
import com.study.goyangrehab.domain.board.service.JobPostingService;
import com.study.goyangrehab.domain.file.entity.Attachment;
import com.study.goyangrehab.dto.BoardRequestDto;
import com.study.goyangrehab.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Log4j2
@Transactional
@Service
@RequiredArgsConstructor
public class JobPostingServiceImpl implements JobPostingService {
    static final Logger logger = LogManager.getLogger(JobPostingServiceImpl.class);
    private final AttachmentService attachmentService;
    private final BoardRepository boardRepository;
    @Override
    public void createJobPosting(BoardRequestDto boardRequestDto) throws IOException {
        List<Attachment> attachments = attachmentService.saveAttachments(boardRequestDto.getAttachmentFiles());
        for (Attachment attachment : attachments) {
            logger.info(attachment.getOriginFilename());
        }

        Board board = boardRequestDto.toEntity();
        boardRepository.save(board);

        JobPosting jobPosting = JobPosting.builder()
                .board(board)
                .build();

        attachments.forEach(jobPosting::addAttachedFile);

        boardRepository.save(jobPosting);
    }

    @Override
    public void updateJobPosting(Long id, BoardRequestDto boardRequestDto) {

    }


}
