package com.study.goyangrehab.domain.program.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLila is a Querydsl query type for Lila
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLila extends EntityPathBase<Lila> {

    private static final long serialVersionUID = -1647852900L;

    public static final QLila lila = new QLila("lila");

    public final QProgram _super = new QProgram(this);

    public final StringPath applicationTarget = createString("applicationTarget");

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

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

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

    public QLila(String variable) {
        super(Lila.class, forVariable(variable));
    }

    public QLila(Path<? extends Lila> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLila(PathMetadata metadata) {
        super(Lila.class, metadata);
    }

}

