package com.study.goyangrehab.domain.program.controller;

import com.study.goyangrehab.domain.program.dto.ProgramRequestDto;
import com.study.goyangrehab.domain.program.dto.ProgramResponseDto;
import com.study.goyangrehab.domain.program.service.impl.SupporterEducationServiceImpl;
import com.study.goyangrehab.domain.program.service.impl.ProgramServiceImpl;
import com.study.goyangrehab.enums.ProgramStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "programs", description = "프로그램 API")
@Slf4j
@RestController
@RequestMapping("/api/program/supporter_education")
@RequiredArgsConstructor
public class SupporterEducationController {
    static final Logger logger = LogManager.getLogger(SupporterEducationController.class);

    private final ProgramServiceImpl programService;
    private final SupporterEducationServiceImpl supporterEducationService;

    @Operation(summary = "활동지원사 리스트 조회", description = "시작 일로 2주 전, 종료일 2주 후 까지 해당 프로그램 리스트 반환")
    @GetMapping
    public ResponseEntity<List<ProgramResponseDto>> getAllInRange() {
        try {
            List<ProgramResponseDto> programResponseDtos = supporterEducationService.getAllInRange();
            return ResponseEntity.ok(programResponseDtos);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "status별 조회", description = "status 파라미터에 따라 ProgramResponseDto List 반환")
    @GetMapping("/{status}")
    public ResponseEntity<List<ProgramResponseDto>> getSupporterEducationByStatus(
            @PathVariable ProgramStatus status
    ) {
        try {
            List<ProgramResponseDto> programResponseDtos = supporterEducationService.getAllByStatus(status);
            return ResponseEntity.ok(programResponseDtos);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary = "조회", description = "조회")
    @GetMapping("/{id}")
    public ResponseEntity<ProgramResponseDto> getSupporterEducation(
            @PathVariable Long id
    ) {
        try {
            ProgramResponseDto programResponseDto = supporterEducationService.getProgram(id);
            return ResponseEntity.ok(programResponseDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "활동지원사 생성", description = "활동지원사 프로그램 생성")
    @PostMapping()
    public ResponseEntity<ProgramResponseDto> createSupporterEducation(
            @Valid @ModelAttribute ProgramRequestDto programRequestDto
    ) {
        try {
            ProgramResponseDto programResponseDto = supporterEducationService.createProgram(programRequestDto);
            return ResponseEntity.ok(programResponseDto);
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "활동지원사 변경", description = "활동지원사 프로그램 변경")
    @PutMapping("/{id}")
    public ResponseEntity<ProgramResponseDto> updateSupporterEducation(
            @NotNull @PathVariable Long id,
            @Valid @ModelAttribute ProgramRequestDto programRequestDto
    ) {
        try {
            ProgramResponseDto programResponseDto = supporterEducationService.updateProgram(id, programRequestDto);
            return ResponseEntity.ok(programResponseDto);
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
