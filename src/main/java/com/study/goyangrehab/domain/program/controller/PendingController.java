package com.study.goyangrehab.domain.program.controller;

import com.study.goyangrehab.domain.program.service.impl.PendingServiceImpl;
import com.study.goyangrehab.domain.user.dto.UserResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "programs", description = "프로그램 API")
@Slf4j
@RestController
@RequestMapping("/api/program/pending")
@RequiredArgsConstructor
public class PendingController {

    static final Logger logger = LogManager.getLogger(PendingController.class);
    private final PendingServiceImpl pendingService;

    @GetMapping("/arte")
    public ResponseEntity<List<UserResponseDto>> getArteUsers() {
        try {
            List<UserResponseDto> userResponseDtos = pendingService.getPendingArteUsers();
            return ResponseEntity.ok(userResponseDtos);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/digital_education")
    public ResponseEntity<List<UserResponseDto>> getDigitalEducationUsers() {
        try {
            List<UserResponseDto> userResponseDtos = pendingService.getPendingEducationUsers();
            return ResponseEntity.ok(userResponseDtos);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/digital_experience")
    public ResponseEntity<List<UserResponseDto>> getDigitalExperienceUsers() {
        try {
            List<UserResponseDto> userResponseDtos = pendingService.getPendingExperienceUsers();
            return ResponseEntity.ok(userResponseDtos);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/lila")
    public ResponseEntity<List<UserResponseDto>> getLilaUsers() {
        try {
            List<UserResponseDto> userResponseDtos = pendingService.getPendingLilaUsers();
            return ResponseEntity.ok(userResponseDtos);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/nuri")
    public ResponseEntity<List<UserResponseDto>> getNuriUsers() {
        try {
            List<UserResponseDto> userResponseDtos = pendingService.getPendingNuriClassUsers();
            return ResponseEntity.ok(userResponseDtos);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/supporter_education")
    public ResponseEntity<List<UserResponseDto>> getSupporterEducationUsers() {
        try {
            List<UserResponseDto> userResponseDtos = pendingService.getPendingSupporterEducationUsers();
            return ResponseEntity.ok(userResponseDtos);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/accept")
    public ResponseEntity<UserResponseDto> acceptUser(
            @RequestParam Long programId,
            @NotBlank @RequestParam String userId
    ) {
        try {
            UserResponseDto userResponseDto = pendingService.acceptUser(programId, userId);
            return ResponseEntity.ok(userResponseDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/deny")
    public ResponseEntity<UserResponseDto> denyUser(
            @RequestParam Long programId,
            @NotBlank @RequestParam String userId
    ) {
        try {
            UserResponseDto userResponseDto = pendingService.denyUser(programId, userId);
            return ResponseEntity.ok(userResponseDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
