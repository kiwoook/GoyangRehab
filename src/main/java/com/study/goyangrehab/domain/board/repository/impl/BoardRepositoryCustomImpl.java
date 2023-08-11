package com.study.goyangrehab.domain.board.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.domain.board.entity.boards.*;
import com.study.goyangrehab.domain.board.repository.BoardRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.List;

import static com.study.goyangrehab.domain.board.entity.boards.QReply.reply;
import static com.study.goyangrehab.domain.board.entity.QBoard.board;
import static com.study.goyangrehab.domain.board.entity.boards.QEvent.event;
import static com.study.goyangrehab.domain.board.entity.boards.QJobPosting.jobPosting;
import static com.study.goyangrehab.domain.board.entity.boards.QQnA.qnA;
import static com.study.goyangrehab.domain.board.entity.boards.QNotice.notice;
import static com.study.goyangrehab.domain.board.entity.boards.QNews.news;
import static com.study.goyangrehab.domain.board.entity.boards.QFree.free;


@Repository
@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Board> findJobPostingWithReply(Pageable pageable) {
        return jpaQueryFactory
                .selectFrom(board)
                .leftJoin(jobPosting).on(jobPosting.id.eq(board.id))
                .leftJoin(reply).on(reply.board.id.eq(board.id))
                .orderBy(board.id.desc(), reply.id.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<Board> findQnAWithReply(Pageable pageable) {
        return jpaQueryFactory
                .selectFrom(board)
                .leftJoin(qnA).on(qnA.id.eq(board.id))
                .leftJoin(reply).on(reply.board.id.eq(board.id))
                .orderBy(board.id.desc(), reply.id.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<Event> findEventsForMonthAndYear(YearMonth yearMonth) {

        return jpaQueryFactory
                .selectFrom(event)
                .where(event.date.between(yearMonth.atDay(1), yearMonth.atEndOfMonth()))
                .fetch();
    }

    @Override
    public List<Notice> findNotice(Pageable pageable) {
        return jpaQueryFactory
                .selectFrom(notice)
                .orderBy(notice.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<News> findNews(Pageable pageable) {
        return jpaQueryFactory
                .selectFrom(news)
                .orderBy(news.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<Free> findFree(Pageable pageable) {
        return jpaQueryFactory
                .selectFrom(free)
                .orderBy(free.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
