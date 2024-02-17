package com.hackerspace2024.webservice.dto.files;

import com.hackerspace2024.webservice.domain.files.Files;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class FileDto {
    private Long id;
    private String realName;
    private String savedName;
    private String savedPath;
    private Long relatedPostId;

    public Files toEntity() {
        return Files.builder()
                .id(id)
                .realName(realName)
                .savedName(savedName)
                .savedPath(savedPath)
                .build();
    }

    public FileDto(Files files) {
        this.id = files.getId();
        this.realName = files.getRealName();
        this.savedName = files.getSavedName();
        this.savedPath = files.getSavedPath();
        this.relatedPostId = files.getRelatedPostId();
    }

    @Builder
    public FileDto(Long id, String realName, String savedName, String savedPath, Long relatedPostId) {
        this.id = id;
        this.realName = realName;
        this.savedName = savedName;
        this.savedPath = savedPath;
        this.relatedPostId = relatedPostId;
    }
}
