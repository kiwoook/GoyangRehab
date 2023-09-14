package com.study.goyangrehab.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserProgram is a Querydsl query type for UserProgram
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserProgram extends EntityPathBase<UserProgram> {

    private static final long serialVersionUID = -228376294L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserProgram userProgram = new QUserProgram("userProgram");

    public final EnumPath<com.study.goyangrehab.enums.ProgramCategory> category = createEnum("category", com.study.goyangrehab.enums.ProgramCategory.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.study.goyangrehab.domain.program.entity.QProgram program;

    public final DateTimePath<java.time.LocalDateTime> registerEndTime = createDateTime("registerEndTime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> registerStartTime = createDateTime("registerStartTime", java.time.LocalDateTime.class);

    public final EnumPath<com.study.goyangrehab.enums.PendingStatus> status = createEnum("status", com.study.goyangrehab.enums.PendingStatus.class);

    public final QUser user;

    public QUserProgram(String variable) {
        this(UserProgram.class, forVariable(variable), INITS);
    }

    public QUserProgram(Path<? extends UserProgram> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserProgram(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserProgram(PathMetadata metadata, PathInits inits) {
        this(UserProgram.class, metadata, inits);
    }

    public QUserProgram(Class<? extends UserProgram> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.program = inits.isInitialized("program") ? new com.study.goyangrehab.domain.program.entity.QProgram(forProperty("program")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

