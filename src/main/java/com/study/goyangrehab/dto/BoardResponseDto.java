package com.study.goyangrehab.dto;

import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.domain.file.entity.Attachment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;
    private String creator;
    private Integer view;
    private List<Attachment> attachmentFiles;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.creator = board.getCreator();
        this.view = board.getView();
        this.attachmentFiles = board.getAttachedFiles();
    }

}
