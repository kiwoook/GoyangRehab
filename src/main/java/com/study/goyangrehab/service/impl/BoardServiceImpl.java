package com.study.goyangrehab.service.impl;

import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.domain.board.entity.boards.Reply;
import com.study.goyangrehab.domain.board.repository.BoardRepository;
import com.study.goyangrehab.domain.file.FileStore;
import com.study.goyangrehab.domain.file.entity.Attachment;
import com.study.goyangrehab.dto.BoardRequestDto;
import com.study.goyangrehab.service.AttachmentService;
import com.study.goyangrehab.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    static final Logger logger = LogManager.getLogger(BoardServiceImpl.class);
    private final AttachmentService attachmentService;
    private final BoardRepository boardRepository;
    private final FileStore fileStore;

    @Override
    public Board getBoardById(Long id) {
        return null;
    }

    @Override
    public List<Board> getAllBoards() {
        return null;
    }

    @Override
    public List<Board> getBoardsByAuthor(String author) {
        return null;
    }

    @Override
    public void createBoard(BoardRequestDto boardRequestDto) throws IOException {
        List<Attachment> attachments = attachmentService.saveAttachments(boardRequestDto.getAttachmentFiles());
        for (Attachment attachment : attachments) {
            log.info(attachment.getOriginFilename());
        }
        Board board = boardRequestDto.toEntity();
        attachments.forEach(board::addAttachedFile);

        boardRepository.save(board);
    }

    @Override
    public void updateBoard(BoardRequestDto boardRequestDto) {

    }


    @Override
    public void deleteBoard(Long id) {

    }

    @Override
    public void addReplyToBoard(Long boardId, Reply reply) {

    }
}
