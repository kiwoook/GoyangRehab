package com.study.goyangrehab.domain.board.entity;

import com.study.goyangrehab.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;

    @ColumnDefault("0")
    private Integer view;

    @Builder
    public Board(Long id, String title, String content, String author, Integer view) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.view = view;
    }
}
