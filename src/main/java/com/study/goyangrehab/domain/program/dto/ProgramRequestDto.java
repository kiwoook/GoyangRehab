package com.study.goyangrehab.domain.program.dto;

import com.study.goyangrehab.domain.program.entity.Program;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private LocalDateTime docDeadLine;

    @NotNull
    private LocalDateTime paymentDeadLine;

    private int price;

    private String text;

    private String place;

    private int disabledNum;

    private int nonDisabledNum;

    private String applicationTarget;

    private String courseCategory;


    @Builder
    public ProgramRequestDto(String name, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime registrationStartTime, LocalDateTime registrationEndTime, Integer recruitmentCapacity) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.registrationStartTime = registrationStartTime;
        this.registrationEndTime = registrationEndTime;
        this.recruitmentCapacity = recruitmentCapacity;
    }

    public static Program toEntity(ProgramRequestDto programRequestDto) {
        return Program.builder()
                .name(programRequestDto.getName())
                .startTime(programRequestDto.getStartTime())
                .endTime(programRequestDto.getEndTime())
                .recruitmentCapacity(programRequestDto.getRecruitmentCapacity())
                .registerStartTime(programRequestDto.getRegistrationStartTime())
                .registerEndTime(programRequestDto.getRegistrationEndTime())
                .docDeadLine(programRequestDto.getDocDeadLine())
                .paymentDeadLine(programRequestDto.getPaymentDeadLine())
                .text(programRequestDto.getText())
                .price(programRequestDto.getPrice())
                .build();
    }
}
