package com.study.goyangrehab.domain.board.repository;

import com.study.goyangrehab.domain.board.entity.boards.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
}
