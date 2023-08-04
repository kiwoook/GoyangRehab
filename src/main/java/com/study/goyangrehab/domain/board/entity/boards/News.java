package com.study.goyangrehab.domain.board.entity.boards;

import com.study.goyangrehab.domain.board.entity.Board;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("news")
public class News extends Board {

    public News(Board board) {
        super(board.getTitle(), board.getContent(), board.getCreator(), board.getView(), board.getAttachedFiles());
    }
}
