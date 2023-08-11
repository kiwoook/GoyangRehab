package com.study.goyangrehab.domain.board.service.impl;

import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.domain.board.entity.boards.JobPosting;
import com.study.goyangrehab.domain.board.repository.BoardRepository;
import com.study.goyangrehab.domain.board.service.JobPostingService;
import com.study.goyangrehab.domain.file.entity.Attachment;
import com.study.goyangrehab.dto.BoardRequestDto;
import com.study.goyangrehab.dto.BoardResponseDto;
import com.study.goyangrehab.service.AttachmentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.PageRequest;
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
    public List<BoardResponseDto> getJobPostingList(int page) {
        List<Board> boardList = boardRepository.findJobPostingWithReply(PageRequest.of(page - 1, 15));
        if (boardList.isEmpty()) {
            logger.info("JobPosting BoardList is NULL");
            throw new EntityNotFoundException("JobPosting BoardList is NULL");
        }

        return boardList.stream()
                .map(BoardResponseDto::new)
                .toList();
    }

    @Override
    public void createJobPosting(BoardRequestDto boardRequestDto) throws IOException {
        List<Attachment> attachments = attachmentService.saveAttachments(boardRequestDto.getAttachmentFiles());
        for (Attachment attachment : attachments) {
            logger.info(attachment.getOriginFilename());
        }

        Board board = boardRequestDto.toEntity();
        boardRepository.save(board);

        JobPosting jobPosting = new JobPosting(board);

        attachments.forEach(jobPosting::addAttachedFile);

        boardRepository.save(jobPosting);
    }

    @Override
    public void updateJobPosting(Long id, BoardRequestDto boardRequestDto) throws IOException, EntityNotFoundException {
        List<Attachment> attachments = attachmentService.saveAttachments(boardRequestDto.getAttachmentFiles());

        Board board = boardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id + "해당 아이디가 존재하지 않습니다."));
        board.update(boardRequestDto, attachments);

        boardRepository.save(board);

        JobPosting jobPosting = new JobPosting(board);


        boardRepository.save(jobPosting);

    }

    @Override
    public void addReplyToJobPosting(Long id, BoardRequestDto boardRequestDto) throws IOException {

    }


}
