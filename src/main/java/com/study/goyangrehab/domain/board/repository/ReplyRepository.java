package com.study.goyangrehab.domain.board.repository;

import com.study.goyangrehab.domain.board.entity.boards.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

   boolean existsByBoardId(Long id);

}
