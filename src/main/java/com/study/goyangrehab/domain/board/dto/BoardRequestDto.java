package com.study.goyangrehab.domain.board.dto;

import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.domain.user.entity.User;
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

    private Map<AttachmentType, List<MultipartFile>> attachmentFiles = new ConcurrentHashMap<>();

    @Builder
    public BoardRequestDto(String title, String content, Map<AttachmentType, List<MultipartFile>> attachmentFiles) {
        this.title = title;
        this.content = content;
        this.attachmentFiles = attachmentFiles;
    }

    public Board toEntity(String userId){
        return Board.builder()
                .title(title)
                .content(content)
                .creator(userId)
                .attachedFiles(new ArrayList<>())
                .build();
    }


}
