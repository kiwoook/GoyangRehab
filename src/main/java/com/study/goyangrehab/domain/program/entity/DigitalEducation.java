package com.study.goyangrehab.domain.program.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@Table(name = "digital_education")
@SuperBuilder
@AllArgsConstructor
public class DigitalEducation extends Program {

}
