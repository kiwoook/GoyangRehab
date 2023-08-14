package com.study.goyangrehab.domain.board.service.impl;

import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.domain.board.entity.boards.Free;
import com.study.goyangrehab.domain.board.entity.boards.Reply;
import com.study.goyangrehab.domain.board.repository.BoardRepository;
import com.study.goyangrehab.domain.board.service.FreeService;
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
@Service
@RequiredArgsConstructor
@Transactional
public class FreeServiceImpl implements FreeService {
    static final Logger logger = LogManager.getLogger(FreeServiceImpl.class);
    private final AttachmentService attachmentService;
    private final BoardRepository boardRepository;

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

    @Override
    public Free createFree(BoardRequestDto boardRequestDto) throws IOException {
        List<Attachment> attachments = attachmentService.saveAttachments(boardRequestDto.getAttachmentFiles());
        for (Attachment attachment : attachments) {
            logger.info(attachment.getOriginFilename());
        }

        Board board = boardRequestDto.toEntity();
        attachments.forEach(board::addAttachedFile);

        return boardRepository.save(new Free(board));
    }

    @Override
    public Free updateFree(Long id, BoardRequestDto boardRequestDto) throws IOException {
        List<Attachment> attachments = attachmentService.saveAttachments(boardRequestDto.getAttachmentFiles());

        Board board = boardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id + "해당 아이디가 존재하지 않습니다."));
        board.update(boardRequestDto, attachments);

        return boardRepository.save(new Free(board));
    }

    @Override
    public Board addReplyToFree(Long id, BoardRequestDto boardRequestDto) throws IOException {
        List<Attachment> attachments = attachmentService.saveAttachments(boardRequestDto.getAttachmentFiles());

        Board free = boardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 ID가 존재하지 않습니다. ID : " + id));

        Reply reply = Reply.createReplyFromDto(boardRequestDto);
        attachments.forEach(reply::addAttachedFile);
        reply.addReply(free);
        return boardRepository.save(reply);
    }
}
