package com.study.goyangrehab.domain.program.dto;

import com.study.goyangrehab.domain.program.entity.Program;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProgramResponseDto {

    private String name;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private LocalDateTime registrationStartTime;

    private LocalDateTime registrationEndTime;

    private int recruitmentCapacity;

    private int currentAttendees;

    private LocalDateTime docDeadLine;

    private LocalDateTime paymentDeadLine;

    private int price;

    private String text;

    public ProgramResponseDto(Program program){
        this.name = program.getName();
        this.startTime = program.getStartTime();
        this.endTime = program.getEndTime();
        this.registrationStartTime = program.getRegisterStartTime();
        this.registrationEndTime = program.getRegisterEndTime();
        this.recruitmentCapacity = program.getRecruitmentCapacity();
        this.currentAttendees = program.getCurrentAttendees();
        this.docDeadLine = program.getDocDeadLine();
        this.paymentDeadLine = program.getPaymentDeadLine();
        this.price = program.getPrice();
        this.text = program.getText();
    }


}
