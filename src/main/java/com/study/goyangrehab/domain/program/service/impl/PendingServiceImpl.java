package com.study.goyangrehab.domain.program.service.impl;

import com.study.goyangrehab.domain.program.entity.Program;
import com.study.goyangrehab.domain.program.repository.ProgramRepository;
import com.study.goyangrehab.domain.program.service.PendingService;
import com.study.goyangrehab.domain.user.dto.UserResponseDto;
import com.study.goyangrehab.domain.user.entity.User;
import com.study.goyangrehab.domain.user.entity.UserProgram;
import com.study.goyangrehab.domain.user.repository.UserProgramRepository;
import com.study.goyangrehab.domain.user.repository.UserRepository;
import com.study.goyangrehab.domain.user.repository.impl.UserProgramRepositoryCustomImpl;
import com.study.goyangrehab.enums.PendingStatus;
import com.study.goyangrehab.enums.ProgramCategory;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PendingServiceImpl implements PendingService {

    private final UserProgramRepositoryCustomImpl userProgramRepositoryCustom;
    private final UserProgramRepository userProgramRepository;
    private final ProgramRepository programRepository;
    private final UserRepository userRepository;

    private static final String USER_TABLE_NOT_FOUND = "해당되는 USER_PROGRAM 테이블이 없습니다";

    @Override
    public List<UserResponseDto> getPendingArteUsers() {
        List<UserProgram> userPrograms = userProgramRepositoryCustom.findPendingUserProgram(LocalDateTime.now(), ProgramCategory.ARTE);
        if(userPrograms.isEmpty()){
            throw new EntityNotFoundException(USER_TABLE_NOT_FOUND);
        }
        return userPrograms.stream().map(UserProgram::getUser).toList().stream().map(UserResponseDto::new).toList();
    }

    @Override
    public List<UserResponseDto> getPendingEducationUsers() {
        List<UserProgram> userPrograms = userProgramRepositoryCustom.findPendingUserProgram(LocalDateTime.now(), ProgramCategory.DIGITAL_EDUCATION);
        if(userPrograms.isEmpty()){
            throw new EntityNotFoundException(USER_TABLE_NOT_FOUND);
        }
        return userPrograms.stream().map(UserProgram::getUser).toList().stream().map(UserResponseDto::new).toList();
    }

    @Override
    public List<UserResponseDto> getPendingExperienceUsers() {
        List<UserProgram> userPrograms = userProgramRepositoryCustom.findPendingUserProgram(LocalDateTime.now(), ProgramCategory.DIGITAL_EXPERIENCE);
        if(userPrograms.isEmpty()){
            throw new EntityNotFoundException(USER_TABLE_NOT_FOUND);
        }
        return userPrograms.stream().map(UserProgram::getUser).toList().stream().map(UserResponseDto::new).toList();
    }

    @Override
    public List<UserResponseDto> getPendingLilaUsers() {
        List<UserProgram> userPrograms = userProgramRepositoryCustom.findPendingUserProgram(LocalDateTime.now(), ProgramCategory.LILA);
        if(userPrograms.isEmpty()){
            throw new EntityNotFoundException(USER_TABLE_NOT_FOUND);
        }
        return userPrograms.stream().map(UserProgram::getUser).toList().stream().map(UserResponseDto::new).toList();
    }

    @Override
    public List<UserResponseDto> getPendingNuriClassUsers() {
        List<UserProgram> userPrograms = userProgramRepositoryCustom.findPendingUserProgram(LocalDateTime.now(), ProgramCategory.NURI_CLASS);
        if(userPrograms.isEmpty()){
            throw new EntityNotFoundException(USER_TABLE_NOT_FOUND);
        }
        return userPrograms.stream().map(UserProgram::getUser).toList().stream().map(UserResponseDto::new).toList();
    }

    @Override
    public List<UserResponseDto> getPendingSupporterEducationUsers() {
        List<UserProgram> userPrograms = userProgramRepositoryCustom.findPendingUserProgram(LocalDateTime.now(), ProgramCategory.SUPPORTER_EDUCATION);
        if(userPrograms.isEmpty()){
            throw new EntityNotFoundException(USER_TABLE_NOT_FOUND);
        }
        return userPrograms.stream().map(UserProgram::getUser).toList().stream().map(UserResponseDto::new).toList();
    }

    @Override
    public UserResponseDto acceptUser(Long programId, String userId) {
        Program program = programRepository.findById(programId).orElseThrow(() -> new EntityNotFoundException("PROGRAM NOT FOUND PROGRAM_ID :"+programId));
        User user = userRepository.findUserByUserId(userId).orElseThrow(() -> new EntityNotFoundException("USER NOT FOUND USER_ID : "+ userId));

        UserProgram userProgram = userProgramRepository.findByUserAndProgram(user, program).orElseThrow(() -> new EntityNotFoundException("USER_PROGRAM NOT FOUND"));
        userProgram.updateStatus(PendingStatus.ACCEPT);

        program.addUser(userProgram);
        user.addProgram(userProgram);

        programRepository.save(program);
        userRepository.save(user);
        userProgramRepository.save(userProgram);

        return new UserResponseDto(user);
    }

    @Override
    public UserResponseDto denyUser(Long programId, String userId) {
        Program program = programRepository.findById(programId).orElseThrow(() -> new EntityNotFoundException("PROGRAM NOT FOUND PROGRAM_ID :"+programId));
        User user = userRepository.findUserByUserId(userId).orElseThrow(() -> new EntityNotFoundException("USER NOT FOUND USER_ID : "+ userId));
        UserProgram userProgram = userProgramRepository.findByUserAndProgram(user, program).orElseThrow(() -> new EntityNotFoundException("USER_PROGRAM NOT FOUND"));

        userProgram.updateStatus(PendingStatus.DENY);

        return new UserResponseDto(user);
    }


}
