package com.study.goyangrehab.domain.user.controller;

import com.study.goyangrehab.domain.program.dto.ProgramResponseDto;
import com.study.goyangrehab.domain.program.service.impl.PendingServiceImpl;
import com.study.goyangrehab.domain.user.dto.UserCreateRequestDto;
import com.study.goyangrehab.domain.user.dto.UserResponseDto;
import com.study.goyangrehab.domain.user.dto.UserUpdateRequestDto;
import com.study.goyangrehab.domain.user.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "user", description = "유저 API")
@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    static final Logger logger = LogManager.getLogger(UserController.class);
    private final UserServiceImpl userService;
    private final PendingServiceImpl pendingService;

    @Operation(summary = "유저 조회", description = "user_id로 유저를 조회함")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        return null;
    }

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "유저 프로그램 내역 확인", description = "유저가 신청했던 프로그램들을 확인합니다.")
    @GetMapping("/programs")
    public ResponseEntity<List<ProgramResponseDto>> getUserPrograms() {
        try {
            List<ProgramResponseDto> programResponseDtos = pendingService.getUserPrograms();
            return ResponseEntity.ok(programResponseDtos);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @Operation(summary = "유저 생성", description = "createDto를 받고 유저를 생성")
    @PostMapping()
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserCreateRequestDto requestDto) {
        try {
            UserResponseDto userResponseDto = userService.create(requestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN') or @userServiceImpl.isOwner(authentication, #userId)")
    @Operation(summary = "회원정보 수정", description = "updateDto를 받고 유저 회원정보 수정")
    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable String userId, @RequestBody UserUpdateRequestDto requestDto) {
        try {
            UserResponseDto userResponseDto = userService.update(userId, requestDto);
            return ResponseEntity.ok(userResponseDto);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN') or @userServiceImpl.isOwner(authentication, #userId)")
    @Operation(summary = "유저 삭제", description = "유저 삭제. 단, Admin과 해당 회원만 삭제 가능")
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        userService.delete(userId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "아이디 중복 확인", description = "파라미터로 userID 받고 중복 확인")
    @GetMapping("/{userId}")
    public ResponseEntity<?> duplicateUserId(@PathVariable String userId) {
        if (userService.isUserIdAvailable(userId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "닉네임 중복 확인", description = "파라미터로 name 받고 중복 확인")
    @GetMapping("/{name}")
    public ResponseEntity<?> duplicateName(@PathVariable String name) {
        if (userService.isNicknameAvailable(name)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


}
