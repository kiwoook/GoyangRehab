package com.study.goyangrehab.domain.board.service.impl;

import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.domain.board.entity.boards.News;
import com.study.goyangrehab.domain.board.repository.BoardRepository;
import com.study.goyangrehab.domain.board.service.NewsService;
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
public class NewsServiceImpl implements NewsService {

    static final Logger logger = LogManager.getLogger(NewsServiceImpl.class);
    private final AttachmentService attachmentService;
    private final BoardRepository boardRepository;

    @Override
    public List<BoardResponseDto> getNewsBoardList(int page) {
        List<News> newsList = boardRepository.findNews(PageRequest.of(page - 1, 15));

        if (newsList.isEmpty()) {
            logger.info("newsList is  Empty");
            throw new EntityNotFoundException("newsList is Empty");
        }

        return newsList.stream()
                .map(BoardResponseDto::new)
                .toList();
    }

    @Override
    public News createNews(BoardRequestDto boardRequestDto) throws IOException {
        List<Attachment> attachments = attachmentService.saveAttachments(boardRequestDto.getAttachmentFiles());
        for (Attachment attachment : attachments) {
            logger.info(attachment.getOriginFilename());
        }

        Board board = boardRequestDto.toEntity();
        attachments.forEach(board::addAttachedFile);

        return boardRepository.save(new News(board));
    }

    @Override
    public News updateNews(Long id, BoardRequestDto boardRequestDto) throws IOException {
        List<Attachment> attachments = attachmentService.saveAttachments(boardRequestDto.getAttachmentFiles());

        Board board = boardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id + "해당 아이디가 존재하지 않습니다."));
        board.update(boardRequestDto, attachments);

        return boardRepository.save(new News(board));
    }


}
