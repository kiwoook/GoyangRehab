package com.study.goyangrehab.domain.board.dto;

import com.study.goyangrehab.domain.board.dto.BoardResponseDto;
import com.study.goyangrehab.domain.board.entity.boards.Event;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventResponseDto extends BoardResponseDto {

    private LocalDate date;

    @Builder
    public EventResponseDto(Event event) {
        super(event);
        this.date = event.getDate();
    }
}
