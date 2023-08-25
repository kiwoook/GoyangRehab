package com.study.goyangrehab.domain.program.repository.impl;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.goyangrehab.domain.program.entity.Program;
import com.study.goyangrehab.domain.program.repository.ProgramRepositoryCustom;
import com.study.goyangrehab.enums.ProgramCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.study.goyangrehab.domain.program.entity.QArte.arte;
import static com.study.goyangrehab.domain.program.entity.QDigitalEducation.digitalEducation;
import static com.study.goyangrehab.domain.program.entity.QDigitalExperience.digitalExperience;
import static com.study.goyangrehab.domain.program.entity.QLila.lila;
import static com.study.goyangrehab.domain.program.entity.QNuriClass.nuriClass;
import static com.study.goyangrehab.domain.program.entity.QProgram.program;
import static com.study.goyangrehab.domain.program.entity.QSupporterEducation.supporterEducation;


@Repository
@RequiredArgsConstructor
public class ProgramRepositoryCustomImpl implements ProgramRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Program> findAllInRange(ProgramCategory programCategory) {

        LocalDateTime twoWeeksAgo = LocalDateTime.now().minusWeeks(2);
        LocalDateTime twoWeeksLater = LocalDateTime.now().plusWeeks(2);

        JPQLQuery<Program> jpqlQuery = jpaQueryFactory.selectFrom(program).where(
                program.startTime.after(twoWeeksAgo)
                        .and(program.endTime.before(twoWeeksLater))
        );

        if (programCategory == ProgramCategory.ARTE) {
            jpqlQuery.join(arte).on(arte.eq(program));
        } else if (programCategory == ProgramCategory.LILA) {
            jpqlQuery.join(lila).on(lila.eq(program));
        } else if (programCategory == ProgramCategory.DIGITAL_EXPERIENCE) {
            jpqlQuery.join(digitalExperience).on(digitalExperience.eq(program));

        } else if (programCategory == ProgramCategory.DIGITAL_EDUCATION) {
            jpqlQuery.join(digitalEducation).on(digitalEducation.eq(program));

        } else if (programCategory == ProgramCategory.NURI_CLASS) {
            jpqlQuery.join(nuriClass).on(nuriClass.eq(program));

        } else if (programCategory == ProgramCategory.SUPPORTER_EDUCATION) {
            jpqlQuery.join(supporterEducation).on(supporterEducation.eq(program));
        }

        return jpqlQuery.fetch();
    }
}
