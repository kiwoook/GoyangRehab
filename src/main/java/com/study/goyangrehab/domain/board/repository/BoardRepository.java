package com.study.goyangrehab.domain.board.repository;


import com.study.goyangrehab.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
