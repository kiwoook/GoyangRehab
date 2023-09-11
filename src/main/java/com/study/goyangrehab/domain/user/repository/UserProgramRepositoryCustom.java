package com.study.goyangrehab.domain.user.repository;

import com.study.goyangrehab.domain.user.entity.UserProgram;
import com.study.goyangrehab.enums.ProgramCategory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserProgramRepositoryCustom {

    List<UserProgram> findPendingUserProgram(LocalDateTime now, ProgramCategory category);
    UserProgram findPendingProgramByUser(String userId, Long programId);

}
