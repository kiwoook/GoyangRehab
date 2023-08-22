package com.study.goyangrehab.domain.board.service.impl;

import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.domain.board.repository.BoardRepository;
import com.study.goyangrehab.domain.board.service.BoardService;
import com.study.goyangrehab.domain.board.util.Util;
import com.study.goyangrehab.domain.file.FileStore;
import com.study.goyangrehab.domain.file.entity.Attachment;
import com.study.goyangrehab.domain.board.dto.BoardRequestDto;
import com.study.goyangrehab.domain.board.dto.BoardResponseDto;
import com.study.goyangrehab.enums.BoardCategory;
import com.study.goyangrehab.enums.SearchType;
import com.study.goyangrehab.service.AttachmentService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    static final Logger logger = LogManager.getLogger(BoardServiceImpl.class);
    private final AttachmentService attachmentService;
    private final BoardRepository boardRepository;
    private final FileStore fileStore;

    @Override
    public BoardResponseDto getBoardById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 ID의 게시글을 찾을 수 없습니다."));

        return new BoardResponseDto(board);
    }

    @Override
    public void increaseViewCount(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 ID의 게시글을 찾을 수 없습니다."));
        board.increaseView();
        boardRepository.save(board);
    }

    @Override
    public int getLastPageOfBoard() {
        return Util.getLastPage(boardRepository.count());
    }


    @Override
    public List<BoardResponseDto> getAllBoards() {
        logger.info("getAllBoards 실행");
        List<Board> boards = boardRepository.findAll();
        return boards.stream().map(BoardResponseDto::new).toList();
    }

    @Override
    public List<BoardResponseDto> search(int page, SearchType searchType, BoardCategory category, String query) {
        if (page < 1){
            throw new IllegalArgumentException();
        }
        List<Board> boardList = boardRepository.searchBoardListDynamically(PageRequest.of(page-1, 15), searchType, category, query);
        logger.info("boardList Size : {}", boardList.size());
        if (boardList.isEmpty()){
            throw new EntityNotFoundException("search : 해당 boardList가 존재하지 않습니다.");
        }

        return boardList.stream().map(BoardResponseDto::new).toList();
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
    public void deleteBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 ID의 게시글이 존재하지 않습니다."));
        boardRepository.delete(board);
    }

}