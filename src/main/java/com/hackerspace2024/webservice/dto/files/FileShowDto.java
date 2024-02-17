package com.hackerspace2024.webservice.dto.files;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FileShowDto {
    private Long id;
    private String realName;

    @Builder
    public FileShowDto(Long id, String realName) {
        this.id = id;
        this.realName = realName;
    }

    @Builder
    public FileShowDto(FileDto dto) {
        this.id = dto.getId();
        this.realName = dto.getRealName();
    }
}
