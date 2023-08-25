package com.study.goyangrehab.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProgramCategory {
    ARTE("arte"),
    DIGITAL_EDUCATION("digital_education"),
    DIGITAL_EXPERIENCE("digital_experience"),
    LILA("lila_academy"),
    NURI_CLASS("nuri_class"),
    SUPPORTER_EDUCATION("supporter_education");

    private final String tableName;
}
