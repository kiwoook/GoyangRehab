package com.study.goyangrehab.domain.program.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@Table(name = "Lila_academy")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class Lila extends Program {
    private String place;
    private int price;

    @Column(name = "application_target")
    private String applicationTarget;
}
