package com.study.goyangrehab.domain.board.service.impl;

import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.domain.board.entity.boards.QnA;
import com.study.goyangrehab.domain.board.entity.boards.Reply;
import com.study.goyangrehab.domain.board.repository.BoardRepository;
import com.study.goyangrehab.domain.board.repository.QnARepository;
import com.study.goyangrehab.domain.board.repository.ReplyRepository;
import com.study.goyangrehab.domain.board.service.QnAService;
import com.study.goyangrehab.domain.board.util.Util;
import com.study.goyangrehab.domain.file.entity.Attachment;
import com.study.goyangrehab.domain.board.dto.BoardRequestDto;
import com.study.goyangrehab.domain.board.dto.BoardResponseDto;
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
public class QnAServiceImpl implements QnAService {

    static final Logger logger = LogManager.getLogger(QnAServiceImpl.class);
    private final AttachmentService attachmentService;
    private final BoardRepository boardRepository;
    private final QnARepository qnaRepository;
    private final ReplyRepository replyRepository;

    @Override
    public List<BoardResponseDto> getQnABoardList(Integer page) {

        List<Board> boardList = boardRepository.findQnAWithReply(PageRequest.of(page - 1, 15));

        if (boardList.isEmpty()) {
            logger.info("QnABoardList is NULL");
            throw new EntityNotFoundException("QNABoardList is NULL");
        }

        return boardList.stream()
                .map(BoardResponseDto::new)
                .toList();
    }

    @Override
    public int getLastPageOfQnA() {
        return Util.getLastPage(qnaRepository.count());
    }

    @Override
    public QnA createQnA(BoardRequestDto boardRequestDto) throws IOException {
        List<Attachment> attachments = attachmentService.saveAttachments(boardRequestDto.getAttachmentFiles());
        for (Attachment attachment : attachments) {
            logger.info(attachment.getOriginFilename());
        }

        Board board = boardRequestDto.toEntity();

        QnA qna = new QnA(board);
        attachments.forEach(qna::addAttachedFile);

        boardRepository.save(qna);
        return qna;
    }

    @Override
    public QnA updateQnA(Long id, BoardRequestDto boardRequestDto) throws IOException, UnsupportedOperationException {

        List<Attachment> attachments = attachmentService.saveAttachments(boardRequestDto.getAttachmentFiles());

        QnA qna = qnaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 ID가 존재하지 않습니다. ID : " + id));

        if (replyRepository.existsByBoardId(id)) {
            logger.info("수정 불가능 id : {}", id);
            throw new UnsupportedOperationException("수정 불가능 ID : " + id);
        }

        qna.update(boardRequestDto, attachments);

        return qna;
    }

    @Override
    public void addReplyToQnA(Long id, BoardRequestDto boardRequestDto) throws IOException {
        List<Attachment> attachments = attachmentService.saveAttachments(boardRequestDto.getAttachmentFiles());

            QnA qna = qnaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 ID가 존재하지 않습니다. ID : " + id));

        Reply reply = Reply.createReplyFromDto(boardRequestDto);
        attachments.forEach(reply::addAttachedFile);
        reply.addReply(qna);
        boardRepository.save(reply);
    }


}
