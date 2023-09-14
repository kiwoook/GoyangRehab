package com.study.goyangrehab.domain.program.controller;

import com.study.goyangrehab.domain.program.dto.ProgramRequestDto;
import com.study.goyangrehab.domain.program.dto.ProgramResponseDto;
import com.study.goyangrehab.domain.program.service.impl.NuriServiceImpl;
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
@RequestMapping("/api/program/nuri")
@RequiredArgsConstructor
public class NuriController {
    static final Logger logger = LogManager.getLogger(NuriController.class);

    private final ProgramServiceImpl programService;
    private final NuriServiceImpl nuriService;

    @Operation(summary = "누리 리스트 조회", description = "시작 일로 2주 전, 종료일 2주 후 까지 해당 프로그램 리스트 반환")
    @GetMapping
    public ResponseEntity<List<ProgramResponseDto>> getAllInRange() {
        try {
            List<ProgramResponseDto> programResponseDtos = nuriService.getAllInRange();
            return ResponseEntity.ok(programResponseDtos);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "status별 조회", description = "status 파라미터에 따라 ProgramResponseDto List 반환")
    @GetMapping("/{status}")
    public ResponseEntity<List<ProgramResponseDto>> getNuriByStatus(
            @PathVariable ProgramStatus status
    ) {
        try {
            List<ProgramResponseDto> programResponseDtos = nuriService.getAllByStatus(status);
            return ResponseEntity.ok(programResponseDtos);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary = "조회", description = "조회")
    @GetMapping("/{id}")
    public ResponseEntity<ProgramResponseDto> getNuri(
            @PathVariable Long id
    ) {
        try {
            ProgramResponseDto programResponseDto = nuriService.getProgram(id);
            return ResponseEntity.ok(programResponseDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "누리 생성", description = "누리 프로그램 생성")
    @PostMapping()
    public ResponseEntity<ProgramResponseDto> createNuri(
            @Valid @ModelAttribute ProgramRequestDto programRequestDto
    ) {
        try {
            ProgramResponseDto programResponseDto = nuriService.createProgram(programRequestDto);
            return ResponseEntity.ok(programResponseDto);
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "누리 변경", description = "누리 프로그램 변경")
    @PutMapping("/{id}")
    public ResponseEntity<ProgramResponseDto> updateNuri(
            @NotNull @PathVariable Long id,
            @Valid @ModelAttribute ProgramRequestDto programRequestDto
    ) {
        try {
            ProgramResponseDto programResponseDto = nuriService.updateProgram(id, programRequestDto);
            return ResponseEntity.ok(programResponseDto);
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
