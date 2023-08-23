package com.study.goyangrehab.domain.user.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.goyangrehab.domain.user.entity.UserProgram;
import com.study.goyangrehab.domain.user.repository.UserProgramRepositoryCustom;
import com.study.goyangrehab.enums.PendingStatus;
import com.study.goyangrehab.enums.ProgramCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.study.goyangrehab.domain.user.entity.QUserProgram.userProgram;


@Repository
@RequiredArgsConstructor
public class UserProgramRepositoryCustomImpl implements UserProgramRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<UserProgram> findPendingUserProgram(LocalDateTime now, ProgramCategory category) {
        return jpaQueryFactory.selectFrom(userProgram)
                .where(userProgram.registerStartTime.before(now)
                        .and(userProgram.registerEndTime.after(now))
                        .and(userProgram.status.eq(PendingStatus.PENDING))
                        .and(userProgram.category.eq(category)))
                .fetch();
    }
}
