package com.study.goyangrehab.domain.board.entity.boards;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QJobPosting is a Querydsl query type for JobPosting
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QJobPosting extends EntityPathBase<JobPosting> {

    private static final long serialVersionUID = 77363258L;

    public static final QJobPosting jobPosting = new QJobPosting("jobPosting");

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

    public QJobPosting(String variable) {
        super(JobPosting.class, forVariable(variable));
    }

    public QJobPosting(Path<? extends JobPosting> path) {
        super(path.getType(), path.getMetadata());
    }

    public QJobPosting(PathMetadata metadata) {
        super(JobPosting.class, metadata);
    }

}

