package com.study.goyangrehab.service.impl;

import com.study.goyangrehab.domain.file.FileStore;
import com.study.goyangrehab.domain.file.entity.Attachment;
import com.study.goyangrehab.domain.file.repository.AttachmentRepository;
import com.study.goyangrehab.enums.AttachmentType;
import com.study.goyangrehab.service.AttachmentService;
import com.study.goyangrehab.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    static final Logger logger = LogManager.getLogger(AttachmentServiceImpl.class);
    private final AttachmentRepository attachmentRepository;
    private final FileStore fileStore;

    @Override
    public List<Attachment> saveAttachments(Map<AttachmentType, List<MultipartFile>> multipartFileListMap) throws IOException {
        List<Attachment> imageFiles = fileStore.storeFiles(multipartFileListMap.get(AttachmentType.IMAGE), AttachmentType.IMAGE);
        List<Attachment> generalFiles = fileStore.storeFiles(multipartFileListMap.get(AttachmentType.GENERAL), AttachmentType.GENERAL);
        return Stream.of(imageFiles, generalFiles)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public Map<AttachmentType, List<Attachment>> findAttachment() {
        List<Attachment> attachments = attachmentRepository.findAll();
        return attachments.stream()
                .collect(Collectors.groupingBy(Attachment::getAttachmentType));
    }
}
