package com.study.goyangrehab.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NoticeCategory {
    NOTICE("공지사항"),
    RECRUIT("이용자 모집"),
    CLOSED("이용자 모집 마감");

    private final String category;
}
