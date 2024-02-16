package com.hackerspace2024.webservice.service;

import com.hackerspace2024.webservice.domain.posts.Posts;
import com.hackerspace2024.webservice.domain.posts.PostsRepository;
import com.hackerspace2024.webservice.dto.posts.PostsSaveRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostsService postsService;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void save_dto_at_posts_table() {
        //given
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .author("user@test.org")
                .content("테스트")
                .title("테스트 타이틀")
                .build();

        //when
        postsService.save(dto);

        //then
        Posts posts = postsRepository.findAll().get(0);
        Assertions.assertThat(posts.getAuthor()).isEqualTo(dto.getAuthor());
        Assertions.assertThat(posts.getContent()).isEqualTo(dto.getContent());
        Assertions.assertThat(posts.getTitle()).isEqualTo(dto.getTitle());
    }


}
