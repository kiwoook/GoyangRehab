package com.study.goyangrehab.domain.board.repository;


import com.study.goyangrehab.domain.board.entity.Board;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {

    @NonNull Optional<Board> findById(@NonNull Long id);


}
