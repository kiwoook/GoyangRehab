package com.study.goyangrehab.domain.program.service;

import com.study.goyangrehab.domain.user.dto.UserResponseDto;

import java.util.List;

public interface PendingService {
    List<UserResponseDto> getPendingArteUsers();

    List<UserResponseDto> getPendingEducationUsers();

    List<UserResponseDto> getPendingExperienceUsers();

    List<UserResponseDto> getPendingLilaUsers();

    List<UserResponseDto> getPendingNuriClassUsers();

    List<UserResponseDto> getPendingSupporterEducationUsers();

    UserResponseDto acceptUser(Long programId, String userId);

    UserResponseDto denyUser(Long programId, String userId);
}
