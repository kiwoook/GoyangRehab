package com.study.goyangrehab.domain.board.dto;

import com.study.goyangrehab.common.dto.PageResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardWithPageResponseDto {
    List<BoardResponseDto> boardResponseDtoList;
    PageResponseDto pageResponseDto;

    public BoardWithPageResponseDto(List<BoardResponseDto> boardResponseDtoList, PageResponseDto pageResponseDto) {
        this.boardResponseDtoList = boardResponseDtoList;
        this.pageResponseDto = pageResponseDto;
    }
}
