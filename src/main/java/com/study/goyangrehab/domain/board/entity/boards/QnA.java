package com.study.goyangrehab.domain.board.entity.boards;

import com.study.goyangrehab.domain.board.entity.Board;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class QnA extends Board {

    @OneToOne(fetch = FetchType.LAZY)
    private Reply reply;

    @Builder
    public QnA(String title, String content, String author, Integer view, Reply reply) {
        super(title, content, author, view);
        this.reply = reply;
    }
}
