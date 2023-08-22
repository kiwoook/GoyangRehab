package com.study.goyangrehab.domain.program.entity;

import com.study.goyangrehab.domain.program.entity.Program;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "support_education_application")
@SuperBuilder
@AllArgsConstructor
public class SupporterEducation extends Program {

    @Column(name = "course_category", length = 50)
    private String courseCategory;


}
