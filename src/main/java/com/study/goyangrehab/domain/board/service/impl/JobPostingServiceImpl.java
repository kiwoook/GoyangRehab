package com.study.goyangrehab.domain.board.service.impl;

import com.study.goyangrehab.domain.board.dto.BoardRequestDto;
import com.study.goyangrehab.domain.board.dto.BoardResponseDto;
import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.domain.board.entity.boards.JobPosting;
import com.study.goyangrehab.domain.board.entity.boards.Reply;
import com.study.goyangrehab.domain.board.repository.BoardRepository;
import com.study.goyangrehab.domain.board.repository.JobPostingRepository;
import com.study.goyangrehab.domain.board.service.JobPostingService;
import com.study.goyangrehab.domain.board.util.Util;
import com.study.goyangrehab.domain.file.entity.Attachment;
import com.study.goyangrehab.domain.file.service.AttachmentService;
import com.study.goyangrehab.domain.user.repository.UserRepository;
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
@Service
@RequiredArgsConstructor
public class JobPostingServiceImpl implements JobPostingService {
    static final Logger logger = LogManager.getLogger(JobPostingServiceImpl.class);
    private final AttachmentService attachmentService;
    private final BoardServiceImpl boardService;
    private final BoardRepository boardRepository;
    private final JobPostingRepository jobPostingRepository;
    private final UserRepository userRepository;

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

    @Transactional
    @Override
    public void createJobPosting(BoardRequestDto boardRequestDto) throws IOException {
        List<Attachment> attachments = attachmentService.saveAttachments(boardRequestDto.getAttachmentFiles());
        Board board = boardService.createBoard(attachments, boardRequestDto);
        JobPosting jobPosting = new JobPosting(board);

        attachments.forEach(jobPosting::addAttachedFile);
        boardRepository.save(jobPosting);
    }

    @Transactional
    @Override
    public void updateJobPosting(Long id, BoardRequestDto boardRequestDto) throws IOException, EntityNotFoundException {
        Board board = boardService.update(id, boardRequestDto);
        JobPosting jobPosting = new JobPosting(board);

        boardRepository.save(jobPosting);
    }

    @Override
    public int getLastPageOfJobPosting() {
        return Util.getLastPage(jobPostingRepository.count());
    }

    @Transactional
    @Override
    public void addReplyToJobPosting(Long id, BoardRequestDto boardRequestDto) throws IOException {
        List<Attachment> attachments = attachmentService.saveAttachments(boardRequestDto.getAttachmentFiles());
        JobPosting jobPosting = jobPostingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 ID가 존재하지 않습니다. ID : " + id));

        Reply reply = Reply.createReplyFromDto(boardRequestDto);
        attachments.forEach(reply::addAttachedFile);
        reply.addReply(jobPosting);
        boardRepository.save(reply);
    }


}
