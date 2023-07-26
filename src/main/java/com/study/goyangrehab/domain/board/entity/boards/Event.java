package com.study.goyangrehab.domain.board.entity.boards;

import com.study.goyangrehab.domain.board.entity.Board;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Getter
@Entity
@NoArgsConstructor
public class Event extends Board {

    private LocalDate date;

    public Event(Long id, String title, String content, String author, Integer view, LocalDate date) {
        super(id, title, content, author, view);
        this.date = date;
    }
}
