package com.study.goyangrehab.domain.board.service.impl;

import com.study.goyangrehab.domain.board.dto.BoardRequestDto;
import com.study.goyangrehab.domain.board.dto.BoardResponseDto;
import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.domain.board.entity.boards.Free;
import com.study.goyangrehab.domain.board.entity.boards.Reply;
import com.study.goyangrehab.domain.board.repository.BoardRepository;
import com.study.goyangrehab.domain.board.repository.FreeRepository;
import com.study.goyangrehab.domain.board.service.FreeService;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor

public class FreeServiceImpl implements FreeService {
    static final Logger logger = LogManager.getLogger(FreeServiceImpl.class);
    private final AttachmentService attachmentService;
    private final BoardServiceImpl boardService;
    private final BoardRepository boardRepository;
    private final FreeRepository freeRepository;


    @Override
    public List<BoardResponseDto> getFreeBoardList(int page) {
        List<Free> freeList = boardRepository.findFree(PageRequest.of(page - 1, 15));

        if (freeList.isEmpty()) {
            logger.info("freeList is Empty");
            throw new EntityNotFoundException("freeList is Empty");
        }

        return freeList.stream()
                .map(BoardResponseDto::new)
                .toList();
    }

    @Transactional
    @Override
    public Free createFree(BoardRequestDto boardRequestDto) throws IOException {
        List<Attachment> attachments = attachmentService.saveAttachments(boardRequestDto.getAttachmentFiles());
        Board board = boardService.createBoard(attachments, boardRequestDto);

        attachments.forEach(board::addAttachedFile);

        return boardRepository.save(new Free(board));
    }

    @Transactional
    @Override
    public Free updateFree(Long id, BoardRequestDto boardRequestDto) throws IOException {
        Board board = boardService.update(id, boardRequestDto);

        return boardRepository.save(new Free(board));
    }

    @Override
    public int getLastPageOfFree() {
        return Util.getLastPage(freeRepository.count());
    }

    @Transactional
    @Override
    public Board addReplyToFree(Long id, BoardRequestDto boardRequestDto) throws IOException {
        List<Attachment> attachments = attachmentService.saveAttachments(boardRequestDto.getAttachmentFiles());

        Board free = boardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 ID가 존재하지 않습니다. ID : " + id));
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        Reply reply = Reply.createReplyFromDto(boardRequestDto, userId);
        attachments.forEach(reply::addAttachedFile);
        reply.addReply(free);
        return boardRepository.save(reply);
    }
}
