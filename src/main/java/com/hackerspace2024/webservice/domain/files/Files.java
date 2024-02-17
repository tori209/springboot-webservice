package com.hackerspace2024.webservice.domain.files;

import com.hackerspace2024.webservice.domain.posts.Posts;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Files {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 업로드 당시 파일의 실제 이름
    @Column(nullable = false)
    private String realName;

    // 서버 저장 시 사용되는 파일 이름
    @Column(nullable = false)
    private String savedName;

    // 파일이 저장된 서버 디렉토리 위치
    @Column(nullable = false)
    private String savedPath;

    // 저장을 요구한 게시글의 Id
    @Column(nullable = false)
    private Long relatedPostId;

    @Builder
    public Files(Long id, String realName, String savedName, String savedPath, Long relatedPostId) {
        this.id = id;
        this.realName = realName;
        this.savedName = savedName;
        this.savedPath = savedPath;
        this.relatedPostId = relatedPostId;
    }
}
