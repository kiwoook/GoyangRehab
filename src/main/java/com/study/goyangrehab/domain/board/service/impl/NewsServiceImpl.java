package com.study.goyangrehab.domain.board.service.impl;

import com.study.goyangrehab.domain.board.dto.BoardRequestDto;
import com.study.goyangrehab.domain.board.dto.BoardResponseDto;
import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.domain.board.entity.boards.News;
import com.study.goyangrehab.domain.board.repository.BoardRepository;
import com.study.goyangrehab.domain.board.repository.NewsRepository;
import com.study.goyangrehab.domain.board.service.NewsService;
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
public class NewsServiceImpl implements NewsService {

    static final Logger logger = LogManager.getLogger(NewsServiceImpl.class);
    private final AttachmentService attachmentService;
    private final BoardServiceImpl boardService;
    private final BoardRepository boardRepository;
    private final NewsRepository newsRepository;
    private final UserRepository userRepository;


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

    @Transactional
    @Override
    public int getLastPageOfNews() {
        return Util.getLastPage(newsRepository.count());
    }

    @Override
    public News createNews(BoardRequestDto boardRequestDto) throws IOException {
        List<Attachment> attachments = attachmentService.saveAttachments(boardRequestDto.getAttachmentFiles());
        Board board = boardService.createBoard(attachments, boardRequestDto);

        attachments.forEach(board::addAttachedFile);

        return boardRepository.save(new News(board));
    }

    @Transactional
    @Override
    public News updateNews(Long id, BoardRequestDto boardRequestDto) throws IOException {
        Board board = boardService.update(id, boardRequestDto);

        return boardRepository.save(new News(board));
    }


}
