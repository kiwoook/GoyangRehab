package com.study.goyangrehab.dto;

import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.enums.AttachmentType;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequestDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String creator;

    private Map<AttachmentType, List<MultipartFile>> attachmentFiles = new ConcurrentHashMap<>();

    @Builder
    public BoardRequestDto(String title, String content, String creator, Map<AttachmentType, List<MultipartFile>> attachmentFiles) {
        this.title = title;
        this.content = content;
        this.creator = creator;
        this.attachmentFiles = attachmentFiles;
    }

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .creator(creator)
                .attachedFiles(new ArrayList<>())
                .view(0)
                .build();
    }


}
