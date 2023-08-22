package com.study.goyangrehab.domain.board.service;

import com.study.goyangrehab.domain.board.entity.boards.Notice;
import com.study.goyangrehab.domain.board.dto.BoardRequestDto;
import com.study.goyangrehab.domain.board.dto.BoardResponseDto;
import com.study.goyangrehab.enums.NoticeCategory;

import java.io.IOException;
import java.util.List;

public interface NoticeService {

    List<BoardResponseDto> getNoticeBoardList(int page);

    int getLastPageOfNotice();

    Notice createNotice(BoardRequestDto boardRequestDto, NoticeCategory category) throws IOException;

    Notice updateNotice(Long id, BoardRequestDto boardRequestDto, NoticeCategory category) throws IOException;


}
