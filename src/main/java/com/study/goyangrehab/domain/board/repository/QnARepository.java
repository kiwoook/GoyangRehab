package com.study.goyangrehab.domain.board.repository;

import com.study.goyangrehab.domain.board.entity.boards.QnA;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QnARepository extends JpaRepository<QnA, Long>{
    @NonNull Optional<QnA> findById(@NonNull Long id);

}
