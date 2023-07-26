package com.study.goyangrehab.domain.board.entity.boards;

import com.study.goyangrehab.domain.board.entity.Board;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class QnA extends Board {

    @OneToOne
    private Reply reply;

    public QnA(Long id, String title, String content, String author, Integer view, Reply reply) {
        super(id, title, content, author, view);
        this.reply = reply;
    }
}
