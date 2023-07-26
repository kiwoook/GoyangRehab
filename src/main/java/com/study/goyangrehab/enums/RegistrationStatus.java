package com.study.goyangrehab.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RegistrationStatus {
    WAITING("접수 대기"),
    IN_PROGRESS("접수 중"),
    CLOSED("접수 마감");

    private final String status;
}
