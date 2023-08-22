package com.study.goyangrehab.domain.program.entity;

import com.study.goyangrehab.domain.program.entity.Program;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@Table(name = "Lila_academy")
@SuperBuilder
@AllArgsConstructor
public class Lila extends Program {


}
