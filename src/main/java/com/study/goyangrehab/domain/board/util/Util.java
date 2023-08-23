package com.study.goyangrehab.domain.board.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Util {

    public static int getLastPage(Long totalPage) {
        return (int) Math.ceil((double) totalPage / 15);
    }
}
