package com.hackerspace2024.webservice.service;

import com.hackerspace2024.webservice.domain.posts.PostsRepository;
import com.hackerspace2024.webservice.dto.posts.PostShowResponseDto;
import com.hackerspace2024.webservice.dto.posts.PostsMainResponseDto;
import com.hackerspace2024.webservice.dto.posts.PostsSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PostsService {
    private PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto dto) {
        return postsRepository.save(dto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<PostsMainResponseDto> findAllDesc() {
        return postsRepository.findAllDesc()
                .map(PostsMainResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PostShowResponseDto findOneById(Long id) {
        List<PostShowResponseDto> list = postsRepository
                .findOneById(id)
                .map(PostShowResponseDto::new)
                .toList();
        if (list.isEmpty())
            return null;
        else
            return list.get(0);
    }
}
