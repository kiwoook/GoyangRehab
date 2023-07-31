package com.study.goyangrehab.domain.board.service.impl;

import com.study.goyangrehab.domain.board.repository.BoardRepository;
import com.study.goyangrehab.domain.board.service.EventService;
import com.study.goyangrehab.dto.BoardRequestDto;
import com.study.goyangrehab.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Log4j2
@Transactional
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    static final Logger logger = LogManager.getLogger(EventServiceImpl.class);
    private final AttachmentService attachmentService;
    private final BoardRepository boardRepository;
    @Override
    public void createEvent(BoardRequestDto boardRequestDto, LocalDate date) {

    }

    @Override
    public void updateEvent(Long id, BoardRequestDto boardRequestDto, LocalDate date) {

    }
}
