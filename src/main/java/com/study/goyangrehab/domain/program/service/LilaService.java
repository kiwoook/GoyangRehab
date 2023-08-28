package com.study.goyangrehab.domain.program.service;

import com.study.goyangrehab.domain.program.dto.ProgramRequestDto;
import com.study.goyangrehab.domain.program.dto.ProgramResponseDto;
import com.study.goyangrehab.enums.ProgramStatus;

import java.util.List;

public interface LilaService {
    List<ProgramResponseDto> getAllInRange();

    List<ProgramResponseDto> getAllByStatus(ProgramStatus status);

    ProgramResponseDto getProgram(Long programId);

    ProgramResponseDto createProgram(ProgramRequestDto programRequestDto);

    ProgramResponseDto updateProgram(Long programId, ProgramRequestDto programRequestDto);
}
