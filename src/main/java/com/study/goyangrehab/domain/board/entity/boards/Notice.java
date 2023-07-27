package com.study.goyangrehab.domain.board.entity.boards;

import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.enums.NoticeCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Notice extends Board {


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NoticeCategory category;


    @Builder

    public Notice(String title, String content, String author, Integer view, NoticeCategory category) {
        super(title, content, author, view);
        this.category = category;
    }
}
