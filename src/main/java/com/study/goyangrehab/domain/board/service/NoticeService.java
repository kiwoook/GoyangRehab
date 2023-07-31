package com.study.goyangrehab.domain.board.service;

import com.study.goyangrehab.dto.BoardRequestDto;
import com.study.goyangrehab.enums.NoticeCategory;

import java.io.IOException;

public interface NoticeService {

    void createNotice(BoardRequestDto boardRequestDto, NoticeCategory category) throws IOException;

    void updateNotice(Long id, BoardRequestDto boardRequestDto, NoticeCategory category) throws IOException;


}
