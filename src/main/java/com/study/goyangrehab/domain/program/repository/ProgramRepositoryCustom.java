package com.study.goyangrehab.domain.program.repository;

import com.study.goyangrehab.domain.program.entity.Program;
import com.study.goyangrehab.enums.ProgramCategory;

import java.util.List;

public interface ProgramRepositoryCustom {

    List<Program> findAllInRange(ProgramCategory programCategory);
}
