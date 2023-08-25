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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("supporter_education")
@SuperBuilder
public class SupporterEducation extends Program {

    @Column(name = "course_category", length = 50)
    private String courseCategory;


}
