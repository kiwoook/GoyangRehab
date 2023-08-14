package com.study.goyangrehab.domain.board.repository.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.domain.board.entity.boards.Event;
import com.study.goyangrehab.domain.board.entity.boards.Free;
import com.study.goyangrehab.domain.board.entity.boards.News;
import com.study.goyangrehab.domain.board.entity.boards.Notice;
import com.study.goyangrehab.domain.board.repository.BoardRepositoryCustom;
import com.study.goyangrehab.enums.BoardCategory;
import com.study.goyangrehab.enums.SearchType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.List;

import static com.study.goyangrehab.domain.board.entity.QBoard.board;
import static com.study.goyangrehab.domain.board.entity.boards.QEvent.event;
import static com.study.goyangrehab.domain.board.entity.boards.QFree.free;
import static com.study.goyangrehab.domain.board.entity.boards.QJobPosting.jobPosting;
import static com.study.goyangrehab.domain.board.entity.boards.QNews.news;
import static com.study.goyangrehab.domain.board.entity.boards.QNotice.notice;
import static com.study.goyangrehab.domain.board.entity.boards.QQnA.qnA;
import static com.study.goyangrehab.domain.board.entity.boards.QReply.reply;


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

    @Override
    public List<Board> searchBoardListDynamically(Pageable pageable, SearchType searchType, BoardCategory category, String query) {
        BooleanBuilder builder = new BooleanBuilder();

        // TODO 유저 아이디에 대한 분기를 만들어야 함.

        if (searchType == SearchType.TITLE) {
            builder.and(board.title.containsIgnoreCase(query));
        } else if (searchType == SearchType.CONTENT) {
            builder.and(board.content.containsIgnoreCase(query));
        } else if (searchType == SearchType.NAME) {
            builder.and(board.creator.eq(query));
        } else if (searchType == SearchType.TITLE_CONTENT) {
            builder.and(board.title.containsIgnoreCase(query)
                    .or(board.content.containsIgnoreCase(query)));
        }

        JPQLQuery<Board> jpqlQuery = jpaQueryFactory.selectFrom(board).where(builder).orderBy(board.id.desc());

        if (category == BoardCategory.FREE) {
            jpqlQuery.join(free).on(free.id.eq(board.id));

        } else if (category == BoardCategory.NEWS) {
            jpqlQuery.join(news).on(news.id.eq(board.id));
        } else if (category == BoardCategory.QNA) {
            jpqlQuery.join(qnA).on(qnA.id.eq(board.id));
        } else if (category == BoardCategory.JOB_POSTING) {
            jpqlQuery.join(jobPosting).on(jobPosting.id.eq(board.id));
        } else if (category == BoardCategory.NOTICE) {
            jpqlQuery.join(notice).on(notice.id.eq(board.id));
        }

        return jpqlQuery.fetch();
    }
}


