package io.cupokki.jpa.service.Impl;

import io.cupokki.jpa.dto.PostCreateDto;
import io.cupokki.jpa.dto.PostDto;
import io.cupokki.jpa.entity.Post;
import io.cupokki.jpa.repository.PostRepository;
import io.cupokki.jpa.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class PostServiceImpl implements PostService {

    private static final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @Override
    public PostDto createPost(PostCreateDto postCreateDto) {
        var post = new io.cupokki.jpa.entity.Post();
        post.setTitle(postCreateDto.getTitle());
        post.setContent(postCreateDto.getContent());
        post.setMemberSeq(postCreateDto.getMemberSeq());
        PostDto postDto = new PostDto(postRepository.save(post));
        log.info(postDto.getContent());
        return postDto;
    }

    @Override
    public List<PostDto> findAll() {
//        return List.of();
        return postRepository.findAll().stream().map(post->
                post.fromPostDto(
                    post.getPostSeq(),
                    post.getTitle(),
                    post.getContent(),
                    post.getMemberSeq(),
                    post.getCreateAt(),
                    post.getUpdateAt()
            )
        ).collect(Collectors.toList());
    }

    @Override
    public PostDto getById(Long postSeq) {
        var result = postRepository.findById(postSeq).orElseThrow(()-> new RuntimeException("err"));
        return new PostDto(result);
    }

    @Override
    public PostDto findByKeyword(String keyword) {
        return new PostDto();
    }

    @Override
    public boolean delete(Long postSeq, Long MemberSeq) {
        return false;
    }
}
