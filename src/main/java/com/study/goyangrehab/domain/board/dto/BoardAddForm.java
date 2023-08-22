package com.study.goyangrehab.domain.board.dto;

import com.study.goyangrehab.enums.AttachmentType;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
@NoArgsConstructor
public class BoardAddForm {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String creator;

    private List<MultipartFile> imageFiles;
    private List<MultipartFile> generalFiles;

    @Builder
    public BoardAddForm(String title, String content, String creator, List<MultipartFile> imageFiles, List<MultipartFile> generalFiles) {
        this.title = title;
        this.content = content;
        this.creator = creator;
        this.imageFiles = (imageFiles != null) ? imageFiles : new ArrayList<>();
        this.generalFiles = (generalFiles != null) ? generalFiles : new ArrayList<>();
    }


    public BoardRequestDto createBoardPostDto() {
        Map<AttachmentType, List<MultipartFile>> attachments = getAttachmentTypeListMap();
        return BoardRequestDto.builder()
                .title(title)
                .content(content)
                .creator(creator)
                .attachmentFiles(attachments)
                .build();
    }

    private Map<AttachmentType, List<MultipartFile>> getAttachmentTypeListMap() {
        Map<AttachmentType, List<MultipartFile>> attachments = new ConcurrentHashMap<>();
        if (imageFiles != null) {
            attachments.put(AttachmentType.IMAGE, imageFiles);
        }
        if (generalFiles != null) {
            attachments.put(AttachmentType.GENERAL, generalFiles);
        }
        return attachments;
    }
}


