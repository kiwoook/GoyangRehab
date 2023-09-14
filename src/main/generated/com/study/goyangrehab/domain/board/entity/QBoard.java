package com.study.goyangrehab.domain.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = -2142768678L;

    public static final QBoard board = new QBoard("board");

    public final com.study.goyangrehab.common.QBaseTimeEntity _super = new com.study.goyangrehab.common.QBaseTimeEntity(this);

    public final ListPath<com.study.goyangrehab.domain.file.entity.Attachment, com.study.goyangrehab.domain.file.entity.QAttachment> attachedFiles = this.<com.study.goyangrehab.domain.file.entity.Attachment, com.study.goyangrehab.domain.file.entity.QAttachment>createList("attachedFiles", com.study.goyangrehab.domain.file.entity.Attachment.class, com.study.goyangrehab.domain.file.entity.QAttachment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath creator = createString("creator");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath title = createString("title");

    public final NumberPath<Integer> view = createNumber("view", Integer.class);

    public QBoard(String variable) {
        super(Board.class, forVariable(variable));
    }

    public QBoard(Path<? extends Board> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoard(PathMetadata metadata) {
        super(Board.class, metadata);
    }

}

