package com.study.goyangrehab.domain.program.service.Impl;

import com.study.goyangrehab.domain.program.dto.ProgramRequestDto;
import com.study.goyangrehab.domain.program.dto.ProgramResponseDto;
import com.study.goyangrehab.domain.program.entity.Program;
import com.study.goyangrehab.domain.program.service.ProgramService;
import com.study.goyangrehab.domain.user.dto.UserResponseDto;

import java.util.List;

public class ProgramServiceImpl implements ProgramService {
    @Override
    public ProgramResponseDto getProgramById(Long programId) {
        return null;
    }

    @Override
    public List<ProgramResponseDto> getAllProgramById() {
        return null;
    }

    @Override
    public ProgramResponseDto createProgram(ProgramRequestDto programRequestDto, Program program) {
        return null;
    }

    @Override
    public ProgramResponseDto updateProgram(Long programId, ProgramRequestDto programRequestDto) {
        return null;
    }

    @Override
    public ProgramResponseDto deleteProgram(Long programId) {
        return null;
    }

    @Override
    public UserResponseDto addUserToProgram(Long programId, Long userId) {
        return null;
    }

    @Override
    public UserResponseDto removeUserFromProgram(Long programId, Long userId) {
        return null;
    }

    @Override
    public List<UserResponseDto> getUsersForProgram(Long programId) {
        return null;
    }
}
