package com.study.goyangrehab.domain.user.repository;

import com.study.goyangrehab.domain.program.entity.Program;
import com.study.goyangrehab.domain.user.entity.User;
import com.study.goyangrehab.domain.user.entity.UserProgram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserProgramRepository extends JpaRepository<UserProgram, Long>, UserProgramRepositoryCustom {

    Optional<UserProgram> findByUserAndProgram(User user, Program program);

    Optional<List<UserProgram>> findAllByProgram(Program program);

    Optional<List<UserProgram>> findAllByUser(User user);
}
