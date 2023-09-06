package com.study.goyangrehab.domain.board.service.impl;

import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.domain.board.entity.boards.Event;
import com.study.goyangrehab.domain.board.repository.BoardRepository;
import com.study.goyangrehab.domain.board.repository.EventRepository;
import com.study.goyangrehab.domain.board.service.EventService;
import com.study.goyangrehab.domain.board.util.Util;
import com.study.goyangrehab.domain.file.entity.Attachment;
import com.study.goyangrehab.domain.board.dto.BoardRequestDto;
import com.study.goyangrehab.domain.board.dto.EventResponseDto;
import com.study.goyangrehab.domain.file.service.AttachmentService;
import com.study.goyangrehab.domain.user.entity.User;
import com.study.goyangrehab.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    static final Logger logger = LogManager.getLogger(EventServiceImpl.class);
    private final AttachmentService attachmentService;
    private final BoardServiceImpl boardService;
    private final BoardRepository boardRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Override
    public List<EventResponseDto> getEventsByMonthAndYear(YearMonth yearMonth) {
        List<Event> eventList = boardRepository.findEventsForMonthAndYear(yearMonth);

        return eventList.stream()
                .map(EventResponseDto::new)
                .toList();
    }
    @Transactional
    @Override
    public void createEvent(BoardRequestDto boardRequestDto, LocalDate date) throws IOException, UsernameNotFoundException {
        List<Attachment> attachments = attachmentService.saveAttachments(boardRequestDto.getAttachmentFiles());
        Board board = boardService.createBoard(attachments, boardRequestDto);

        Event event = new Event(board, date);
        attachments.forEach(event::addAttachedFile);
        boardRepository.save(event);
    }

    @Override
    public int getLastPageOfEvent() {
        return Util.getLastPage(eventRepository.count());
    }

    @Transactional
    @Override
    public void updateEvent(Long id, BoardRequestDto boardRequestDto, LocalDate date) throws IOException {
        Board board = boardService.update(id, boardRequestDto);
        Event event = new Event(board, date);

        boardRepository.save(event);
    }
}
