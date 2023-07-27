package com.study.goyangrehab.domain.board.entity.boards;

import com.study.goyangrehab.domain.board.entity.Board;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Event extends Board {

    private LocalDate date;


    @Builder

    public Event(String title, String content, String author, Integer view, LocalDate date) {
        super(title, content, author, view);
        this.date = date;
    }
}
