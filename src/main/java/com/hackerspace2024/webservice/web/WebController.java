package com.hackerspace2024.webservice.web;

import com.hackerspace2024.webservice.dto.files.FileDto;
import com.hackerspace2024.webservice.service.FilesService;
import com.hackerspace2024.webservice.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@AllArgsConstructor
public class WebController {

    private PostsService postsService;
    private FilesService filesService;

    @GetMapping("/")
    public String mainDo(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "main";
    }

    @GetMapping("/posts/{id}")
    public String getPostDo(Model model, @PathVariable Long id) {
        model.addAttribute("post", postsService.findOneById(id));
        return "post";
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> fileDownload(@PathVariable("fileId") Long fileId) throws IOException {
        FileDto fileDto = filesService.downloadFile(fileId);
        Path path = Paths.get(fileDto.getSavedPath(), fileDto.getSavedName());
        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream;charset=UTF-8"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(fileDto.getRealName(), StandardCharsets.UTF_8) + "\"")
                .body(resource);
    }
}
