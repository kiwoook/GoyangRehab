package com.study.goyangrehab.domain.program.repository;

import com.study.goyangrehab.domain.program.entity.Arte;
import com.study.goyangrehab.enums.ProgramStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArteRepository extends JpaRepository<Arte, Long> {

    Optional<List<Arte>> findAllByStatusIs(ProgramStatus status);

}
