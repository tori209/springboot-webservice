package com.hackerspace2024.webservice.web;

import com.hackerspace2024.webservice.domain.posts.PostsRepository;
import com.hackerspace2024.webservice.dto.files.FileShowDto;
import com.hackerspace2024.webservice.dto.posts.PostsSaveRequestDto;
import com.hackerspace2024.webservice.service.FilesService;
import com.hackerspace2024.webservice.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/posts")
    public void savePosts(@RequestBody PostsSaveRequestDto dto) {
        postsService.save(dto);
    }

    @GetMapping("/posts/{id}/files")
    public List<FileShowDto> findFilesByPostId(@PathVariable("id") Long id) {
        return filesService.findFilesByPostId(id).stream().map(FileShowDto::new).toList();
    }
}