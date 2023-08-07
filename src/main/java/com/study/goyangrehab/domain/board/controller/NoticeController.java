package com.study.goyangrehab.domain.board.controller;

import com.study.goyangrehab.dto.BoardAddForm;
import com.study.goyangrehab.dto.BoardResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/board/notice")
@RequiredArgsConstructor
public class NoticeController {

    @PostMapping
    public ResponseEntity<BoardResponseDto> createNoitce(
            @ModelAttribute BoardAddForm boardAddForm

    ){
        return null;
    }


}
