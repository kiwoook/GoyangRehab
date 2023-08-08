package com.study.goyangrehab.domain.board.repository;

import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.domain.board.entity.boards.Event;
import com.study.goyangrehab.domain.board.entity.boards.JobPosting;
import com.study.goyangrehab.domain.board.entity.boards.QnA;
import org.springframework.data.domain.Pageable;

import java.time.YearMonth;
import java.util.List;

public interface BoardRepositoryCustom {

    List<Board> findJobPostingWithReply(Pageable pageable);

    List<Board> findQnAWithReply(Pageable pageable);

    List<Event> findEventsForMonthAndYear(YearMonth yearMonth);
}
