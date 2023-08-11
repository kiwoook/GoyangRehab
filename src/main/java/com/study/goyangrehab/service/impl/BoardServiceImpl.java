package com.study.goyangrehab.service.impl;

import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.domain.board.entity.boards.Reply;
import com.study.goyangrehab.domain.board.repository.BoardRepository;
import com.study.goyangrehab.domain.file.FileStore;
import com.study.goyangrehab.domain.file.entity.Attachment;
import com.study.goyangrehab.dto.BoardRequestDto;
import com.study.goyangrehab.dto.BoardResponseDto;
import com.study.goyangrehab.service.AttachmentService;
import com.study.goyangrehab.domain.board.service.BoardService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
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
    public BoardResponseDto getBoardById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 ID의 게시글을 찾을 수 없습니다."));

        return new BoardResponseDto(board);
    }

    @Override
    public List<BoardResponseDto> getAllBoards() {
        logger.info("getAllBoards 실행");
        List<Board> boards = boardRepository.findAll();
        List<BoardResponseDto> boardRequestDtoList = new ArrayList<>();
        for (Board board : boards) {
            boardRequestDtoList.add(new BoardResponseDto(board));
        }
        return boardRequestDtoList;
    }

    @Override
    public List<BoardResponseDto> getBoardsByCreator(String creator) {
        logger.info("getBoardsByCreator 실행");
        List<Board> boards = boardRepository.findAllByCreatorContaining(creator).orElseThrow(() -> new EntityNotFoundException(creator + "가 존재하지 않습니다"));
        List<BoardResponseDto> boardRequestDtoList = new ArrayList<>();
        for (Board board : boards) {
            boardRequestDtoList.add(new BoardResponseDto(board));
        }
        return boardRequestDtoList;
    }

    @Override
    public List<BoardResponseDto> getBoardsByTitle(String title) {
        logger.info("getBoardsByTitle 실행");
        List<Board> boards = boardRepository.findAllByTitleContaining(title).orElseThrow(() -> new EntityNotFoundException(title + "해당 제목이 존재하지 않습니다."));
        List<BoardResponseDto> boardRequestDtoList = new ArrayList<>();
        for (Board board : boards) {
            boardRequestDtoList.add(new BoardResponseDto(board));
        }
        return boardRequestDtoList;
    }

    @Override
    public List<BoardResponseDto> getBoardsByContent(String content) {
        logger.info("getBoardsByContent 실행");
        List<Board> boards = boardRepository.findAllByContentContaining(content).orElseThrow(() -> new EntityNotFoundException(content + "해당 내용이 존재하지 않습니다."));
        List<BoardResponseDto> boardRequestDtoList = new ArrayList<>();
        for (Board board : boards) {
            boardRequestDtoList.add(new BoardResponseDto(board));
        }
        return boardRequestDtoList;
    }

    @Override
    public List<BoardResponseDto> getBoardsByUserId(String userId) {
        return null;
    }

    @Override
    public List<BoardResponseDto> getBoardsByTitleOrContent(String query) {
        logger.info("getBoardsByTitleOrContent");
        List<Board> boards = boardRepository.findByTitleContainingOrContentContaining(query, query).orElseThrow(() -> new EntityNotFoundException(query + "해당 제목과 내용이 존재하지 않습니다"));
        List<BoardResponseDto> boardRequestDtoList = new ArrayList<>();
        for (Board board : boards) {
            boardRequestDtoList.add(new BoardResponseDto(board));
        }
        return boardRequestDtoList;
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
    public void updateBoard(Long id, BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 ID의 게시글을 찾을 수 없습니다."));


    }


    @Override
    public void deleteBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 ID의 게시글이 존재하지 않습니다."));
        boardRepository.delete(board);
    }

    @Override
    public void addReplyToBoard(Long boardId, Reply reply) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new EntityNotFoundException("해당 ID의 게시글을 찾을 수 없습니다."));

    }
}
