package com.study.goyangrehab.domain.program.entity.programs;

import com.study.goyangrehab.domain.program.entity.Program;
import com.study.goyangrehab.enums.ProgramStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "support_education_application")
public class SupporterEducation extends Program {

    @Column(name = "course_category", length = 50)
    private String courseCategory;

    @Builder
    public SupporterEducation(String name, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime registrationStartTime, LocalDateTime registrationEndTime, ProgramStatus status, int recruitmentCapacity, String courseCategory) {
        super(name, startTime, endTime, registrationStartTime, registrationEndTime, status, recruitmentCapacity);
        this.courseCategory = courseCategory;
    }
}
