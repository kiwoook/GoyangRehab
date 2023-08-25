package com.study.goyangrehab.domain.program.repository;

import com.study.goyangrehab.domain.program.entity.DigitalExperience;
import com.study.goyangrehab.enums.ProgramStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DigitalExperienceRepository extends JpaRepository<DigitalExperience, Long> {
    Optional<List<DigitalExperience>> findAllByStatusIs(ProgramStatus status);

}
