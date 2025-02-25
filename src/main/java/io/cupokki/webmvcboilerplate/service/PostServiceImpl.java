package io.cupokki.webmvcboilerplate.service;

import io.cupokki.webmvcboilerplate.dto.PostCreateDto;
import io.cupokki.webmvcboilerplate.dto.PostDto;
import io.cupokki.webmvcboilerplate.repository.PostRepository;
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
        var post = new io.cupokki.webmvcboilerplate.entity.Post();
        post.setTitle(postCreateDto.getTitle());
        post.setContent(postCreateDto.getContent());
        post.setMemberSeq(postCreateDto.getMemberSeq());
        PostDto postDto = new PostDto(postRepository.save(post));
        return postDto;
    }

    @Override
    public List<PostDto> getAll() {
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
    public PostDto searchByKeyword(String keyword) {
        return new PostDto();
    }

    @Override
    public boolean delete(Long postSeq, Long memberSeq) {
        try {
            var post = postRepository.findById(postSeq).orElseThrow(()-> new RuntimeException("err"));
            if (!post.getMemberSeq().equals(memberSeq))
                return false;
            postRepository.delete(post);
            return true;
        }catch(Exception e) {
            return false;
        }


    }
}
