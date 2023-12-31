package com.study.goyangrehab.domain.board.service.impl;

import com.study.goyangrehab.domain.board.dto.BoardRequestDto;
import com.study.goyangrehab.domain.board.dto.BoardResponseDto;
import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.domain.board.entity.boards.Notice;
import com.study.goyangrehab.domain.board.repository.BoardRepository;
import com.study.goyangrehab.domain.board.repository.NoticeRepository;
import com.study.goyangrehab.domain.board.service.NoticeService;
import com.study.goyangrehab.domain.board.util.Util;
import com.study.goyangrehab.domain.file.entity.Attachment;
import com.study.goyangrehab.domain.file.service.AttachmentService;
import com.study.goyangrehab.domain.user.repository.UserRepository;
import com.study.goyangrehab.enums.NoticeCategory;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
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
public class NoticeServiceImpl implements NoticeService {
    static final Logger logger = LogManager.getLogger(NoticeServiceImpl.class);
    private final AttachmentService attachmentService;
    private final BoardServiceImpl boardService;
    private final BoardRepository boardRepository;
    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;


    @Override
    public List<BoardResponseDto> getNoticeBoardList(int page) {
        List<Notice> noticeList = boardRepository.findNotice(PageRequest.of(page - 1, 15));

        if (noticeList.isEmpty()) {
            logger.info("noticeList is Empty");
            throw new EntityNotFoundException("noticeList is Empty");
        }

        return noticeList.stream()
                .map(BoardResponseDto::new)
                .toList();
    }

    @Override
    public int getLastPageOfNotice() {
        return Util.getLastPage(noticeRepository.count());
    }

    @Transactional
    @Override
    public Notice createNotice(BoardRequestDto boardRequestDto, @NonNull NoticeCategory category) throws IOException {
        List<Attachment> attachments = attachmentService.saveAttachments(boardRequestDto.getAttachmentFiles());
        Board board = boardService.createBoard(attachments, boardRequestDto);

        Notice notice = new Notice(board, category);
        attachments.forEach(notice::addAttachedFile);

        return boardRepository.save(notice);
    }

    @Transactional
    @Override
    public Notice updateNotice(Long id, BoardRequestDto boardRequestDto, NoticeCategory category) throws IOException, EntityNotFoundException {
        Board board = boardService.update(id, boardRequestDto);

        Notice notice = new Notice(board, category);

        return boardRepository.save(notice);
    }
}
