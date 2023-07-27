package com.study.goyangrehab.domain.file.repository;

import com.study.goyangrehab.domain.file.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
