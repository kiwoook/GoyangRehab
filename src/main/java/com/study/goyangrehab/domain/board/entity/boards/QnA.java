package com.study.goyangrehab.domain.board.entity.boards;

import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.domain.file.entity.Attachment;
import jakarta.persistence.DiscriminatorValue;
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
@DiscriminatorValue("qna")
public class QnA extends Board {

    @OneToOne(fetch = FetchType.LAZY)
    private Reply reply;

    @Builder
    public QnA(Board board, Reply reply) {
        super(board.getTitle(), board.getContent(), board.getCreator(), board.getView(), board.getAttachedFiles());
        this.reply = reply;
    }

    @Override
    public String toString() {
        return "id = " + this.getId() ;
    }
}
