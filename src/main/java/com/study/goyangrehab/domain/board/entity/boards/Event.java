package com.study.goyangrehab.domain.board.entity.boards;

import com.study.goyangrehab.domain.board.entity.Board;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("event")
public class Event extends Board {

    private LocalDate date;

    public Event(Board board, LocalDate date) {
        super(board.getTitle(), board.getContent(), board.getCreator(), board.getView(), board.getAttachedFiles());
        this.date = date;
    }
}
