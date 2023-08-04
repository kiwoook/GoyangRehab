package com.study.goyangrehab.domain.board.entity.boards;

import com.study.goyangrehab.domain.board.entity.Board;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@DiscriminatorValue("qna")
public class QnA extends Board {

    @OneToOne(fetch = FetchType.LAZY)
    private Reply reply;


    public QnA(Board board) {
        super(board.getTitle(), board.getContent(), board.getCreator(), board.getView(), board.getAttachedFiles());
    }

    public void addReply(Reply reply) {
        this.reply = reply;
    }

    @Override
    public String toString() {
        return "id = " + this.getId();
    }
}
