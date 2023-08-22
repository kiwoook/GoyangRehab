package com.study.goyangrehab.domain.board.repository;

import com.study.goyangrehab.domain.board.entity.boards.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
}
