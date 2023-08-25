package com.study.goyangrehab.domain.program.repository;

import com.study.goyangrehab.domain.program.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Long>, ProgramRepositoryCustom {
}
