package com.study.goyangrehab.domain.board.entity.boards;

import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.domain.board.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@DiscriminatorValue("reply")
public class Reply extends Board {


    @OneToOne
    private Board board;

    public Reply(Board board) {
        super("Re:"+board.getTitle(), board.getContent(), board.getCreator(), board.getView(), board.getAttachedFiles());
    }

    public void addReply(Board board){
        this.board = board;
    }

    public static Reply createReplyFromDto(BoardRequestDto boardRequestDto, String userId) {
        return new Reply(boardRequestDto.toEntity(userId));
    }
}
