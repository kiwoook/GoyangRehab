package com.study.goyangrehab.domain.program.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QArte is a Querydsl query type for Arte
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QArte extends EntityPathBase<Arte> {

    private static final long serialVersionUID = -1648171700L;

    public static final QArte arte = new QArte("arte");

    public final QProgram _super = new QProgram(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final NumberPath<Integer> currentAttendees = _super.currentAttendees;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> docDeadLine = _super.docDeadLine;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> endTime = _super.endTime;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    //inherited
    public final StringPath name = _super.name;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> paymentDeadLine = _super.paymentDeadLine;

    public final StringPath place = createString("place");

    //inherited
    public final NumberPath<Integer> price = _super.price;

    //inherited
    public final NumberPath<Integer> recruitmentCapacity = _super.recruitmentCapacity;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> registerEndTime = _super.registerEndTime;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> registerStartTime = _super.registerStartTime;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> startTime = _super.startTime;

    //inherited
    public final EnumPath<com.study.goyangrehab.enums.ProgramStatus> status = _super.status;

    //inherited
    public final StringPath text = _super.text;

    //inherited
    public final SetPath<com.study.goyangrehab.domain.user.entity.UserProgram, com.study.goyangrehab.domain.user.entity.QUserProgram> users = _super.users;

    public QArte(String variable) {
        super(Arte.class, forVariable(variable));
    }

    public QArte(Path<? extends Arte> path) {
        super(path.getType(), path.getMetadata());
    }

    public QArte(PathMetadata metadata) {
        super(Arte.class, metadata);
    }

}

