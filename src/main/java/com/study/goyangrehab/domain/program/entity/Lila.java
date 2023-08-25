package com.study.goyangrehab.domain.program.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@DiscriminatorValue("lila_academy")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class Lila extends Program {
    private String place;
    private int price;

    @Column(name = "application_target")
    private String applicationTarget;
}
