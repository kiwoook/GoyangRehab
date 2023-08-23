package com.study.goyangrehab.domain.program.service;

import com.study.goyangrehab.domain.program.dto.ProgramResponseDto;
import com.study.goyangrehab.domain.user.dto.UserResponseDto;

import java.util.List;

public interface ProgramService {

    ProgramResponseDto getProgramById(Long programId);

    List<UserResponseDto> getAllUserByProgramId(Long programId);

    ProgramResponseDto deleteProgram(Long programId);

    UserResponseDto removeUserFromProgram(Long programId, String userId);

    List<UserResponseDto> getUsersForProgram(String programId);
}
