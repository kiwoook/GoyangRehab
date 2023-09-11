package com.study.goyangrehab.domain.program.service;

import com.study.goyangrehab.domain.program.dto.ProgramResponseDto;
import com.study.goyangrehab.domain.user.dto.UserResponseDto;
import com.study.goyangrehab.enums.ProgramCategory;

import java.util.List;

public interface ProgramService {

    ProgramResponseDto getProgramById(Long programId);

    List<UserResponseDto> getAllUserByProgramId(Long programId);

    UserResponseDto apply(Long programId, ProgramCategory category);

    ProgramResponseDto delete(Long programId);

    UserResponseDto removeUserFromProgram(Long programId, String userId);

    List<UserResponseDto> getUsersForProgram(String programId);
}
