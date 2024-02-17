package com.hackerspace2024.webservice.web;

import com.hackerspace2024.webservice.domain.posts.PostsRepository;
import com.hackerspace2024.webservice.dto.files.FileDto;
import com.hackerspace2024.webservice.dto.files.FileShowDto;
import com.hackerspace2024.webservice.dto.posts.PostsSaveRequestDto;
import com.hackerspace2024.webservice.service.FilesService;
import com.hackerspace2024.webservice.service.PostsService;
import com.hackerspace2024.webservice.util.MD5Generator;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
public class WebRestController {

    private PostsService postsService;
    private FilesService filesService;

    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld";
    }

    @PostMapping(value="/posts", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void savePosts(@RequestBody PostsSaveRequestDto dto) {
        postsService.save(dto);
    }

    @PostMapping(value="/posts", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void savePostsWithFile(PostsSaveRequestDto dto, @RequestParam(name="files") MultipartFile files) {
        try {
            String realName = files.getOriginalFilename();
            // 이름만 같은 다른 파일과 중복되는 경우 배제 차원에서 시간 정보 추가하여 해싱.
            String saveName = new MD5Generator(realName + LocalDateTime.now().toString()).toString();
            // 파일 저장 위치. 서버의 Working Dir.의 'files' 폴더에 저장.
            String savePath = System.getProperty("user.dir") + "\\files";

            // 없을 시 경로 생성. (오류 방지)
            if (!new File(savePath).exists()) {
                try {
                    new File(savePath).mkdir();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
            String filePath = savePath + "\\" + saveName;
            files.transferTo(new File(filePath));
            Long postId = postsService.save(dto);
            System.out.println(postId);
            FileDto fileDto = FileDto.builder()
                    .realName(realName)
                    .savedName(saveName)
                    .savedPath(savePath)
                    .relatedPostId(postId)
                    .build();
            filesService.uploadFile(fileDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/posts/{id}/files")
    public List<FileShowDto> findFilesByPostId(@PathVariable("id") Long id) {
        return filesService.findFilesByPostId(id).stream().map(FileShowDto::new).toList();
    }
}