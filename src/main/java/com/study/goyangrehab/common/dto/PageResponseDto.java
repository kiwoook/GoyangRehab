package com.study.goyangrehab.common.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PageResponseDto {
    int page;

    public PageResponseDto(int page) {
        this.page = page;
    }
}
