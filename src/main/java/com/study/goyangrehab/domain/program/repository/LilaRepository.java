package com.study.goyangrehab.domain.program.repository;

import com.study.goyangrehab.domain.program.entity.Lila;
import com.study.goyangrehab.enums.ProgramStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LilaRepository extends JpaRepository<Lila, Long> {
    Optional<List<Lila>> findAllByStatusIs(ProgramStatus status);

}
