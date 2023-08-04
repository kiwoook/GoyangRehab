package com.study.goyangrehab.domain.board.entity.boards;

import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.enums.NoticeCategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("notice")
public class Notice extends Board {

    @Column(nullable = false, name = "notice_category")
    @Enumerated(EnumType.STRING)
    private NoticeCategory category;


    public Notice(Board board, NoticeCategory category) {
        super(board.getTitle(), board.getContent(), board.getCreator(), board.getView(), board.getAttachedFiles());
        this.category = category;
    }
}
