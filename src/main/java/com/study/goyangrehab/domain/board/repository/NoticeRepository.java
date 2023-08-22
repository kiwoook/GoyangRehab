package com.study.goyangrehab.domain.board.repository;

import com.study.goyangrehab.domain.board.entity.boards.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
