package io.cupokki.jpa.service;

import io.cupokki.jpa.dto.PostCreateDto;
import io.cupokki.jpa.entity.Post;
import io.cupokki.jpa.repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest // JPA 관련 컴포넌트만 로드하여 가벼운 테스트 환경 제공
@Transactional // 각 테스트 후 롤백됨 (자동 적용)
//@Rollback // 테스트 후 데이터베이스 변경 방지
class PostServiceTest {

    @Autowired
    PostRepository postRepository;
    PostService postService;

    @BeforeEach
    void setUp() {
        postService = new PostServiceImpl(postRepository);
    }


    @Test
    @DisplayName("포스트 생성")
    void createPost() {
        //given
        PostCreateDto dto = new PostCreateDto();
        dto.setTitle("title");
        dto.setContent("content");
        dto.setMemberSeq(1L);

        //when
        postService.createPost(dto);

        //then
        try {
            Post result = postRepository.findById(1L)
                    .orElseThrow(()-> new RuntimeException());
            Assertions.assertThat(result.getTitle()).isEqualTo(dto.getTitle());
        } catch (RuntimeException e) {
            Assertions.fail();
        }
    }

    @Test
    void findAll() {
        //given
        PostCreateDto dto1 = new PostCreateDto();
        dto1.setTitle("title1");
        dto1.setContent("content1");
        dto1.setMemberSeq(1L);

        PostCreateDto dto2 = new PostCreateDto();
        dto2.setTitle("title2");
        dto2.setContent("content2");
        dto2.setMemberSeq(2L);

        //when
        postService.createPost(dto1);
        postService.createPost(dto2);

        //then
        List<Post> result = postRepository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void getById() {
        //given
        PostCreateDto dto1 = PostCreateDto.builder()
                .title("title1")
                .content("content1")
                .memberSeq(1L)
                .build();
        //when
        postService.createPost(dto1);

        //then
        try {
            var post = postRepository.findAll().get(0);
            var result = postService.getById(post.getPostSeq());
            Assertions.assertThat(result.getPostSeq()).isEqualTo(post.getPostSeq());
        } catch (Exception exception) {
            Assertions.fail();
        }

    }

    @Test
    void searchByKeyword() {
    }

    @Test
    void delete() {
        //given
        PostCreateDto dto1 = PostCreateDto.builder()
                .title("title1")
                .content("content1")
                .memberSeq(1L)
                .build();

        //when
        postService.createPost(dto1);

        //then
        try {
            var post = postRepository.findAll().get(0);
            postService.delete(post.getPostSeq(), 1L);
            Assertions.assertThat(postRepository.findAll().size()).isEqualTo(0);
        } catch (Exception exception) {
            Assertions.fail();
        }
    }
}