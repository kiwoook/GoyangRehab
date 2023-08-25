package com.study.goyangrehab.domain.program.entity;

import com.study.goyangrehab.domain.program.dto.ProgramRequestDto;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("arte_academy")
@SuperBuilder
public class Arte extends Program {
    private String place;

    @Override
    public void update(ProgramRequestDto programRequestDto) {
        super.update(programRequestDto);
        this.place = programRequestDto.getPlace();
    }


}
