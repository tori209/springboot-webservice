package com.hackerspace2024.webservice.service;

import com.hackerspace2024.webservice.domain.files.Files;
import com.hackerspace2024.webservice.domain.files.FilesRepository;
import com.hackerspace2024.webservice.dto.files.FileDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FilesService {
    private FilesRepository filesRepository;

    public FilesService(FilesRepository filesRepository) {
        this.filesRepository = filesRepository;
    }

    @Transactional
    public Long uploadFile(FileDto reqDto) {
        return filesRepository.save(reqDto.toEntity()).getId();
    }

    @Transactional
    public FileDto downloadFile(Long id) {
        Files file = filesRepository.findById(id).get();

        return FileDto.builder()
                .id(id)
                .realName(file.getRealName())
                .savedName(file.getSavedName())
                .savedPath(file.getSavedPath())
                .build();
    }

    @Transactional
    public List<FileDto> findFilesByPostId(Long postId) {
        return filesRepository
                .findAllByPostId(postId)
                .map(FileDto::new)
                .toList();
    }
}
