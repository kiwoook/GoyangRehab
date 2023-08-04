package com.study.goyangrehab.domain.board.entity.boards;

import com.study.goyangrehab.domain.board.entity.Board;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("free")
public class Free extends Board {

    @OneToOne(fetch = FetchType.LAZY)
    private Reply reply;

    public Free(Board board, Reply reply) {
        super(board.getTitle(), board.getContent(), board.getCreator(), board.getView(), board.getAttachedFiles());
        this.reply = reply;
    }
}
