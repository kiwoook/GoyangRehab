package com.study.goyangrehab.domain.program.entity;

import com.study.goyangrehab.domain.program.entity.Program;
import com.study.goyangrehab.enums.ProgramStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "arte_academy")
@AllArgsConstructor
@SuperBuilder
public class Arte extends Program {


}
