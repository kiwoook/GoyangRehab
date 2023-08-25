package com.study.goyangrehab.domain.program.repository;

import com.study.goyangrehab.domain.program.entity.DigitalEducation;
import com.study.goyangrehab.enums.ProgramStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DigitalEducationRepository extends JpaRepository<DigitalEducation, Long> {
    Optional<List<DigitalEducation>> findAllByStatusIs(ProgramStatus status);
}
