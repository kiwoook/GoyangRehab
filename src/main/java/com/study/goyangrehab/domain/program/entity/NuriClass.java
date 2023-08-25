package com.study.goyangrehab.domain.program.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@DiscriminatorValue("nuri_class")
@SuperBuilder
@NoArgsConstructor
public class NuriClass extends Program {
    private int price;

    @Column(name = "disabled_user_num")
    private int disabledNum;

    @Column(name = "Non_disabled_user_num")
    private int nonDisabledNum;

    @Column(name = "application_target")
    private String applicationTarget;

}
