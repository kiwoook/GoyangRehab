package com.study.goyangrehab.domain.board.entity.boards;

import com.study.goyangrehab.domain.board.entity.Board;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class News extends Board {
    public News(Long id, String title, String content, String author, Integer view) {
        super(id, title, content, author, view);
    }
}
