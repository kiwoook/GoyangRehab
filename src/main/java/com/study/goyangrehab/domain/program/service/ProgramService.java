package com.study.goyangrehab.domain.program.service;

import com.study.goyangrehab.domain.program.dto.ProgramRequestDto;
import com.study.goyangrehab.domain.program.dto.ProgramResponseDto;
import com.study.goyangrehab.domain.program.entity.Program;
import com.study.goyangrehab.domain.user.dto.UserResponseDto;

import java.util.List;

public interface ProgramService {

    ProgramResponseDto getProgramById(Long programId);

    List<ProgramResponseDto> getAllProgramById();

    ProgramResponseDto createProgram(ProgramRequestDto programRequestDto, Program program);

    ProgramResponseDto updateProgram(Long programId, ProgramRequestDto programRequestDto);

    ProgramResponseDto deleteProgram(Long programId);

    UserResponseDto addUserToProgram(Long programId, Long userId);

    UserResponseDto removeUserFromProgram(Long programId, Long userId);

    List<UserResponseDto> getUsersForProgram(Long programId);
}
