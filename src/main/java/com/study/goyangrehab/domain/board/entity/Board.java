package com.study.goyangrehab.domain.board.entity;

import com.study.goyangrehab.common.BaseTimeEntity;
import com.study.goyangrehab.domain.file.entity.Attachment;
import com.study.goyangrehab.dto.BoardRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "category")
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 50, nullable = false)
    private String title;

    @NotBlank
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private String creator;

    @ColumnDefault("0")
    private Integer view;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attachment> attachedFiles = new ArrayList<>();

    @Builder
    public Board(String title, String content, String creator, Integer view, List<Attachment> attachedFiles) {
        this.title = title;
        this.content = content;
        this.creator = creator;
        this.view = view;
        this.attachedFiles = attachedFiles;
    }

    public void addAttachedFile(Attachment attachment) {
        this.attachedFiles.add(attachment);
    }

    public void update(BoardRequestDto boardRequestDto, List<Attachment> attachments) {
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
        this.creator = boardRequestDto.getCreator();
        this.attachedFiles = attachments;
    }

}
