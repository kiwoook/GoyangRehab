package com.study.goyangrehab.domain.program.repository;


import com.study.goyangrehab.domain.program.entity.NuriClass;
import com.study.goyangrehab.enums.ProgramStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NuriRepository extends JpaRepository<NuriClass, Long> {
    Optional<List<NuriClass>> findAllByStatusIs(ProgramStatus status);

}
