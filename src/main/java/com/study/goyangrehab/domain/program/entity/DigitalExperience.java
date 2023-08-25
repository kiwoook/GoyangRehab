package com.study.goyangrehab.domain.program.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@DiscriminatorValue("digital_experience")
@SuperBuilder
@AllArgsConstructor
public class DigitalExperience extends Program {

}
