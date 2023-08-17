package com.study.goyangrehab.domain.program.entity.programs;

import com.study.goyangrehab.domain.program.entity.Program;
import com.study.goyangrehab.enums.ProgramStatus;
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
@Table(name = "nuri_class")
public class NuriClass extends Program {
    @Builder
    public NuriClass(String name, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime registrationStartTime, LocalDateTime registrationEndTime, ProgramStatus status, int recruitmentCapacity) {
        super(name, startTime, endTime, registrationStartTime, registrationEndTime, status, recruitmentCapacity);
    }
}
