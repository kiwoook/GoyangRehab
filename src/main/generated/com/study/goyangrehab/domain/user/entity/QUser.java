package com.study.goyangrehab.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1426098678L;

    public static final QUser user = new QUser("user");

    public final com.study.goyangrehab.common.QBaseTimeEntity _super = new com.study.goyangrehab.common.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final SetPath<UserProgram, QUserProgram> programs = this.<UserProgram, QUserProgram>createSet("programs", UserProgram.class, QUserProgram.class, PathInits.DIRECT2);

    public final SetPath<com.study.goyangrehab.enums.UserAuthority, EnumPath<com.study.goyangrehab.enums.UserAuthority>> roles = this.<com.study.goyangrehab.enums.UserAuthority, EnumPath<com.study.goyangrehab.enums.UserAuthority>>createSet("roles", com.study.goyangrehab.enums.UserAuthority.class, EnumPath.class, PathInits.DIRECT2);

    public final StringPath userId = createString("userId");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

