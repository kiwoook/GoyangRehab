package com.study.goyangrehab.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PendingStatus {
    PENDING,
    ACCEPT,
    DENY,
    TERMINATE
}
