package com.study.goyangrehab.domain.board.entity.boards;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNews is a Querydsl query type for News
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNews extends EntityPathBase<News> {

    private static final long serialVersionUID = 475956744L;

    public static final QNews news = new QNews("news");

    public final com.study.goyangrehab.domain.board.entity.QBoard _super = new com.study.goyangrehab.domain.board.entity.QBoard(this);

    //inherited
    public final ListPath<com.study.goyangrehab.domain.file.entity.Attachment, com.study.goyangrehab.domain.file.entity.QAttachment> attachedFiles = _super.attachedFiles;

    //inherited
    public final StringPath content = _super.content;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final StringPath creator = _super.creator;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    //inherited
    public final StringPath title = _super.title;

    //inherited
    public final NumberPath<Integer> view = _super.view;

    public QNews(String variable) {
        super(News.class, forVariable(variable));
    }

    public QNews(Path<? extends News> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNews(PathMetadata metadata) {
        super(News.class, metadata);
    }

}

