package com.study.goyangrehab.domain.board.service.impl;

import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.domain.board.entity.boards.Notice;
import com.study.goyangrehab.domain.board.repository.BoardRepository;
import com.study.goyangrehab.domain.board.service.NoticeService;
import com.study.goyangrehab.domain.file.entity.Attachment;
import com.study.goyangrehab.dto.BoardRequestDto;
import com.study.goyangrehab.enums.NoticeCategory;
import com.study.goyangrehab.service.AttachmentService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class NoticeServiceImpl implements NoticeService {
    static final Logger logger = LogManager.getLogger(NoticeServiceImpl.class);
    private final AttachmentService attachmentService;
    private final BoardRepository boardRepository;


    @Override
    public void createNotice(BoardRequestDto boardRequestDto, @NonNull NoticeCategory category) throws IOException {
        List<Attachment> attachments = attachmentService.saveAttachments(boardRequestDto.getAttachmentFiles());
        for (Attachment attachment : attachments) {
            logger.info(attachment.getOriginFilename());
        }

        Board board = boardRequestDto.toEntity();
        boardRepository.save(board);

        Notice notice = new Notice(board,category);
        attachments.forEach(notice::addAttachedFile);

        boardRepository.save(notice);
    }

    @Override
    public void updateNotice(Long id, BoardRequestDto boardRequestDto, NoticeCategory category) throws IOException {
        List<Attachment> attachments = attachmentService.saveAttachments(boardRequestDto.getAttachmentFiles());

        Board board = boardRepository.findById(id).orElseThrow(() -> new RuntimeException(id + "해당 아이디가 존재하지 않습니다."));
        board.update(boardRequestDto, attachments);

        boardRepository.save(board);

        Notice notice = new Notice(board, category);

        boardRepository.save(notice);
    }
}
