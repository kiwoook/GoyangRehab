package com.study.goyangrehab.domain.program.dto;

import com.study.goyangrehab.domain.program.entity.Program;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProgramRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private LocalDateTime startTime;

    @NotBlank
    private LocalDateTime endTime;

    @NotBlank
    private LocalDateTime registrationStartTime;

    @NotBlank
    private LocalDateTime registrationEndTime;

    @NotBlank
    private Integer recruitmentCapacity;

    @Builder
    public ProgramRequestDto(String name, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime registrationStartTime, LocalDateTime registrationEndTime, Integer recruitmentCapacity) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.registrationStartTime = registrationStartTime;
        this.registrationEndTime = registrationEndTime;
        this.recruitmentCapacity = recruitmentCapacity;
    }

    public Program toEntity(ProgramRequestDto programRequestDto){
        return Program.builder()
                .name(programRequestDto.getName())
                .startTime(programRequestDto.getStartTime())
                .endTime(programRequestDto.getEndTime())
                .recruitmentCapacity(programRequestDto.getRecruitmentCapacity())
                .registrationStartTime(programRequestDto.getRegistrationStartTime())
                .registrationEndTime(programRequestDto.getRegistrationEndTime())
                .build();
    }
}
