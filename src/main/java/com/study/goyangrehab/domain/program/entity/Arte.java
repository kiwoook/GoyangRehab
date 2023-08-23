package com.study.goyangrehab.domain.program.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "arte_academy")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class Arte extends Program {
    private String place;

    @Builder
    public Arte(String name, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime registrationStartTime, LocalDateTime registrationEndTime, int recruitmentCapacity, LocalDateTime docDeadLine, LocalDateTime paymentDeadLine, String text, int price, String place) {
        super(name, startTime, endTime, registrationStartTime, registrationEndTime, recruitmentCapacity, docDeadLine, paymentDeadLine, text, price);
        this.place = place;
    }
}
