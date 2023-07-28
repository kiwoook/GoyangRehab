package com.study.goyangrehab.service;

import com.study.goyangrehab.domain.file.entity.Attachment;
import com.study.goyangrehab.enums.AttachmentType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public interface AttachmentService {
    List<Attachment> saveAttachments(Map<AttachmentType, List<MultipartFile>> multipartFListMap) throws IOException;

    Map<AttachmentType, List<Attachment>> findAttachment();

}
