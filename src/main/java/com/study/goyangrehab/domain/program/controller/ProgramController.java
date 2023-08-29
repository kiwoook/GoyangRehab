package com.study.goyangrehab.domain.program.controller;

import com.study.goyangrehab.domain.program.dto.ProgramResponseDto;
import com.study.goyangrehab.domain.program.service.ProgramService;
import com.study.goyangrehab.domain.user.dto.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "programs", description = "프로그램 API")
@Log4j2
@RestController
@RequestMapping("/api/program")
@RequiredArgsConstructor
public class ProgramController {
    static final Logger logger = LogManager.getLogger(ProgramController.class);
    private final ProgramService programService;

    // READ
    @Operation(summary = "프로그램 USER 리스트", description = "해당 프로그램의 UserResponseDto List를 반환")
    @GetMapping("{id}/users")
    public ResponseEntity<List<UserResponseDto>> getUsersByProgram(
            @NotNull @PathVariable Long id
    ) {
        try {
            List<UserResponseDto> userResponseDtos = programService.getAllUserByProgramId(id);
            return ResponseEntity.ok().body(userResponseDtos);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    @Operation(summary = "프로그램 ID 삭제", description = "파라미터 ID 값을 통한 프로그램 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<ProgramResponseDto> deleteProgram(
            @PathVariable Long id
    ) {
        try {
            ProgramResponseDto programResponseDto = programService.deleteProgram(id);
            return ResponseEntity.ok().body(programResponseDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "프로그램 유저 삭제", description = "해당 프로그램 ID의 유저 ID 삭제")
    @DeleteMapping("/user")
    public ResponseEntity<UserResponseDto> deleteUserFromProgram(
            @NotBlank @RequestParam(name = "user_id") String userId,
            @NotNull @RequestParam(name = "program_id") Long programId
    ) {
        try {
            UserResponseDto userResponseDto = programService.removeUserFromProgram(programId, userId);
            return ResponseEntity.ok().body(userResponseDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
