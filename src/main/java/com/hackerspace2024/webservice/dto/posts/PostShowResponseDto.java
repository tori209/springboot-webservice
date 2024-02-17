package com.hackerspace2024.webservice.dto.posts;

import com.hackerspace2024.webservice.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Getter
public class PostShowResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;
    private String createdDate;
    private String modifiedDate;

    @Builder
    public PostShowResponseDto(Posts entry) {
        id = entry.getId();
        title = entry.getTitle();
        content = entry.getContent();
        author = entry.getAuthor();
        createdDate = toStringDateTime(entry.getCreatedDate());
        modifiedDate = toStringDateTime(entry.getModifiedDate());
    }

    private String toStringDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Optional.ofNullable(localDateTime)
                .map(formatter::format)
                .orElse("");
    }
}
