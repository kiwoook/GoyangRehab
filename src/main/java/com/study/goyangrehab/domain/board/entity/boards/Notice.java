package com.study.goyangrehab.domain.board.entity.boards;

import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.domain.file.entity.Attachment;
import com.study.goyangrehab.enums.NoticeCategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("notice")
public class Notice extends Board {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NoticeCategory category;


    @Builder
    public Notice(Board board, NoticeCategory category) {
        super(board.getTitle(), board.getContent(), board.getCreator(), board.getView(), board.getAttachedFiles());
        this.category = category;
    }
}
