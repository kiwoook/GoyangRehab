package com.study.goyangrehab.domain.file.entity;

import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.enums.AttachmentType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name="ATTACHMENT_SEQ_GENERATOR",
        sequenceName = "ATTACHMENT_SEQ"
)
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String originFilename;

    private String storeFilename;

    @Enumerated(EnumType.STRING)
    private AttachmentType attachmentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public Attachment(String originFilename, String storeFilename, AttachmentType attachmentType, Board board) {
        this.originFilename = originFilename;
        this.storeFilename = storeFilename;
        this.attachmentType = attachmentType;
        this.board = board;
    }
}
