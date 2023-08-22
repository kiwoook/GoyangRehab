package com.study.goyangrehab.domain.board.util;

public class Util {

    public static int getLastPage(Long totalPage) {
        return (int) Math.ceil((double) totalPage / 15);
    }
}
