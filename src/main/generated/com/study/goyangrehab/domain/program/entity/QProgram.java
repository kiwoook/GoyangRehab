package com.study.goyangrehab.domain.program.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProgram is a Querydsl query type for Program
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProgram extends EntityPathBase<Program> {

    private static final long serialVersionUID = -193778854L;

    public static final QProgram program = new QProgram("program");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> currentAttendees = createNumber("currentAttendees", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> docDeadLine = createDateTime("docDeadLine", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> endTime = createDateTime("endTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> modifiedDate = createDateTime("modifiedDate", java.time.LocalDateTime.class);

    public final StringPath name = createString("name");

    public final DateTimePath<java.time.LocalDateTime> paymentDeadLine = createDateTime("paymentDeadLine", java.time.LocalDateTime.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Integer> recruitmentCapacity = createNumber("recruitmentCapacity", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> registerEndTime = createDateTime("registerEndTime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> registerStartTime = createDateTime("registerStartTime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> startTime = createDateTime("startTime", java.time.LocalDateTime.class);

    public final EnumPath<com.study.goyangrehab.enums.ProgramStatus> status = createEnum("status", com.study.goyangrehab.enums.ProgramStatus.class);

    public final StringPath text = createString("text");

    public final SetPath<com.study.goyangrehab.domain.user.entity.UserProgram, com.study.goyangrehab.domain.user.entity.QUserProgram> users = this.<com.study.goyangrehab.domain.user.entity.UserProgram, com.study.goyangrehab.domain.user.entity.QUserProgram>createSet("users", com.study.goyangrehab.domain.user.entity.UserProgram.class, com.study.goyangrehab.domain.user.entity.QUserProgram.class, PathInits.DIRECT2);

    public QProgram(String variable) {
        super(Program.class, forVariable(variable));
    }

    public QProgram(Path<? extends Program> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProgram(PathMetadata metadata) {
        super(Program.class, metadata);
    }

}

