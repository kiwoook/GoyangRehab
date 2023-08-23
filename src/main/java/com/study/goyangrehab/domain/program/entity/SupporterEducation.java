package com.study.goyangrehab.domain.program.entity;

import jakarta.persistence.Column;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "support_education_application")
@SuperBuilder
public class SupporterEducation extends Program {

    @Column(name = "course_category", length = 50)
    private String courseCategory;

    @Builder
    public SupporterEducation(String name, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime registrationStartTime, LocalDateTime registrationEndTime, int recruitmentCapacity, LocalDateTime docDeadLine, LocalDateTime paymentDeadLine, String text, int price, String courseCategory) {
        super(name, startTime, endTime, registrationStartTime, registrationEndTime, recruitmentCapacity, docDeadLine, paymentDeadLine, text, price);
        this.courseCategory = courseCategory;
    }
}
