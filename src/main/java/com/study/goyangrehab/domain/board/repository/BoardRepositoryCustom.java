package com.study.goyangrehab.domain.board.repository;

import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.domain.board.entity.boards.Event;
import com.study.goyangrehab.domain.board.entity.boards.Free;
import com.study.goyangrehab.domain.board.entity.boards.News;
import com.study.goyangrehab.domain.board.entity.boards.Notice;
import com.study.goyangrehab.enums.BoardCategory;
import com.study.goyangrehab.enums.SearchType;
import org.springframework.data.domain.Pageable;

import java.time.YearMonth;
import java.util.List;

public interface BoardRepositoryCustom {

    List<Board> findJobPostingWithReply(Pageable pageable);

    List<Board> findQnAWithReply(Pageable pageable);

    List<Event> findEventsForMonthAndYear(YearMonth yearMonth);

    List<Notice> findNotice(Pageable pageable);

    List<News> findNews(Pageable pageable);

    List<Free> findFree(Pageable pageable);


    List<Board> searchBoardListDynamically(Pageable pageable, SearchType searchType, BoardCategory category, String query);
}
