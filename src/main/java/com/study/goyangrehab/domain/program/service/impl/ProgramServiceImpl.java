package com.study.goyangrehab.domain.program.service.impl;

import com.study.goyangrehab.domain.program.dto.ProgramResponseDto;
import com.study.goyangrehab.domain.program.entity.Program;
import com.study.goyangrehab.domain.program.repository.ProgramRepository;
import com.study.goyangrehab.domain.program.service.ProgramService;
import com.study.goyangrehab.domain.user.dto.UserResponseDto;
import com.study.goyangrehab.domain.user.entity.User;
import com.study.goyangrehab.domain.user.entity.UserProgram;
import com.study.goyangrehab.domain.user.repository.UserProgramRepository;
import com.study.goyangrehab.domain.user.repository.UserRepository;
import com.study.goyangrehab.enums.PendingStatus;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Transactional
@Service
public class ProgramServiceImpl implements ProgramService {
    static final Logger logger = LogManager.getLogger(ProgramServiceImpl.class);
    private static final String PROGRAM_NOT_FOUND = "해당 프로그램이 존재하지 않습니다. ID : ";
    private static final String USER_NOT_FOUND = "해당 유저가 존재하지 않습니다. USER : ";
    private static final String USER_PROGRAM_NOT_FOUND = "해당 USER_PROGRAM 테이블이 존재하지 않습니다.";
    private final ProgramRepository programRepository;
    private final UserProgramRepository userProgramRepository;
    private final UserRepository userRepository;

    @Override
    public ProgramResponseDto getProgramById(Long programId) {
        Program program = programRepository.findById(programId).orElseThrow(() -> new EntityNotFoundException(PROGRAM_NOT_FOUND + programId));
        return new ProgramResponseDto(program);
    }

    @Override
    public List<UserResponseDto> getAllUserByProgramId(Long programId) {
        Program program = programRepository.findById(programId).orElseThrow(() -> new EntityNotFoundException(PROGRAM_NOT_FOUND + programId));
        List<UserProgram> userProgramList = userProgramRepository.findAllByProgram(program).orElseThrow(() -> new EntityNotFoundException("해당 user_program 테이블이 존재하지 않습니다. ID : " + programId));

        return userProgramList.stream().map(UserProgram::getUser).toList().stream().map(UserResponseDto::new).toList();
    }

    @Override
    public ProgramResponseDto deleteProgram(Long programId) {
        Program program = programRepository.findById(programId).orElseThrow(() -> new EntityNotFoundException(PROGRAM_NOT_FOUND + programId));
        programRepository.delete(program);
        return new ProgramResponseDto(program);
    }

    // TODO 이거 프로그램별로 다 만들어야함 ㅋㅋ
//    @Override
//    public UserResponseDto applyUserToProgram(Long programId, String userId) {
//        User user = userRepository.findUserByUserId(userId).orElseThrow(() -> new EntityNotFoundException("유저가 존재하지 않습니다. userId : " + userId));
//        Program program = programRepository.findById(programId).orElseThrow(() -> new EntityNotFoundException("프로그램이 존재하지 않습니다. programId : " + programId));
//
//        UserProgram userProgram = new UserProgram(user, program);
//        userProgramRepository.save(userProgram);
//
//        return new UserResponseDto(user);
//    }

    @Override
    public UserResponseDto removeUserFromProgram(Long programId, String userId) {
        Program program = programRepository.findById(programId).orElseThrow(() -> new EntityNotFoundException(PROGRAM_NOT_FOUND + programId));
        User user = userRepository.findUserByUserId(userId).orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND + userId));

        UserProgram userProgram = userProgramRepository.findByUserAndProgram(user, program).orElseThrow(() -> new EntityNotFoundException(USER_PROGRAM_NOT_FOUND));

        if (userProgram.getStatus() == PendingStatus.ACCEPT) {
            program.removeUser(userProgram);
            user.leaveProgram(userProgram);
            userProgram.updateStatus(PendingStatus.TERMINATE);

            programRepository.save(program);
            userRepository.save(user);
            userProgramRepository.save(userProgram);
        } else {
            throw new UnsupportedOperationException("작동 불가");
        }

        return new UserResponseDto(user);
    }

    @Override
    public List<UserResponseDto> getUsersForProgram(String programId) {
        return null;
    }
}
