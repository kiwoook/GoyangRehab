package com.study.goyangrehab.domain.board.entity.boards;

import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.enums.NoticeCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Notice extends Board {

    @Column(nullable = false)
    private NoticeCategory category;


    @Builder
    public Notice(Long id, String title, String content, String author, Integer view, NoticeCategory category) {
        super(id, title, content, author, view);
        this.category = category;
    }
}
