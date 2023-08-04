package com.study.goyangrehab.domain.board.entity.boards;

import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.dto.BoardRequestDto;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@DiscriminatorValue("reply")
public class Reply extends Board {

    public Reply(Board board) {
        super(board.getTitle(), board.getContent(), board.getCreator(), board.getView(), board.getAttachedFiles());
    }

    public static Reply createReplyFromDto(BoardRequestDto boardRequestDto) {
        return new Reply(boardRequestDto.toEntity());
    }
}
