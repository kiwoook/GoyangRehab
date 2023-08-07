package com.study.goyangrehab.domain.board.service.impl;

import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.domain.board.entity.boards.QnA;
import com.study.goyangrehab.domain.board.entity.boards.Reply;
import com.study.goyangrehab.domain.board.repository.BoardRepository;
import com.study.goyangrehab.domain.board.repository.QnARepository;
import com.study.goyangrehab.domain.board.service.QnAService;
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
public class QnAServiceImpl implements QnAService {

    static final Logger logger = LogManager.getLogger(QnAServiceImpl.class);
    private final AttachmentService attachmentService;
    private final BoardRepository boardRepository;
    private final QnARepository qnaRepository;

    @Override
    public List<BoardResponseDto> getQnABoardList(Integer page) {

        List<Board> boardList = boardRepository.findQnAWithReply(PageRequest.of(page, 15));

        if(boardList.isEmpty()){
            logger.info("QnABoardList is NULL");
            throw new NullPointerException("QNABoardList is NULL");
        }

        return boardList.stream()
                .map(BoardResponseDto::new)
                .toList();
    }

    @Override
    public void createQnA(BoardRequestDto boardRequestDto) throws IOException {
        List<Attachment> attachments = attachmentService.saveAttachments(boardRequestDto.getAttachmentFiles());
        for (Attachment attachment : attachments) {
            logger.info(attachment.getOriginFilename());
        }

        Board board = boardRequestDto.toEntity();
        boardRepository.save(board);

        QnA qna = new QnA(board);
        attachments.forEach(qna::addAttachedFile);

        boardRepository.save(qna);
    }

    @Override
    public void updateQnA(Long id, BoardRequestDto boardRequestDto) throws IOException {

        List<Attachment> attachments = attachmentService.saveAttachments(boardRequestDto.getAttachmentFiles());

        QnA qna = qnaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 ID가 존재하지 않습니다. ID : " + id));

        // 서비스에서 QnA 객체에 Reply 객체가 연결되어 있는지 확인?
        if (qna.getReply() == null) {
            logger.info("수정 불가능");
            throw new NullPointerException("수정 불가능 객체 아이디 : " + qna);
        }

        Board board = qna;
        board.update(boardRequestDto, attachments);

        boardRepository.save(board);

        qna = new QnA(board);

        boardRepository.save(qna);
    }

    @Override
    public void addReplyToQnA(Long id, BoardRequestDto boardRequestDto) throws IOException {
        List<Attachment> attachments = attachmentService.saveAttachments(boardRequestDto.getAttachmentFiles());

        QnA qna = qnaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 ID가 존재하지 않습니다. ID : " + id));

        Reply reply = Reply.createReplyFromDto(boardRequestDto);
        attachments.forEach(reply::addAttachedFile);
        boardRepository.save(reply);

        qna.addReply(reply);

        boardRepository.save(qna);
    }


}
