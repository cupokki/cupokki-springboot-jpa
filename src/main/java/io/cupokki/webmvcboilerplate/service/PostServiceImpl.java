package io.cupokki.webmvcboilerplate.service;

import io.cupokki.webmvcboilerplate.dto.PostCreateDto;
import io.cupokki.webmvcboilerplate.dto.PostDto;
import io.cupokki.webmvcboilerplate.entity.Member;
import io.cupokki.webmvcboilerplate.entity.Post;
import io.cupokki.webmvcboilerplate.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.collect;

public class PostServiceImpl implements PostService {

    private static final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostCreateDto postCreateDto) {
        var post = Post.builder()
                .title(postCreateDto.getTitle())
                .content(postCreateDto.getContent())
                .member(postCreateDto.getMember())
                .build();

        var saved = postRepository.save(post);
        return PostDto.builder()
                .title(saved.getTitle())
                .content(saved.getContent())
                .member(saved.getMember())
                .createAt(saved.getCreateAt())
                .updateAt(saved.getUpdateAt())
                .build();
    }

    @Override
    public List<PostDto> getAll() {
        return new ArrayList<PostDto>();
    }

    @Override
    public PostDto getById(Long postSeq) {
        var result = postRepository.findById(postSeq).orElseThrow(()-> new RuntimeException("err"));
        return PostDto.builder()
                .title(result.getTitle())
                .content(result.getContent())
                .member(result.getMember())
                .createAt(result.getCreateAt())
                .updateAt(result.getUpdateAt())
                .build();
    }

    @Override
    public PostDto searchByKeyword(String keyword) {
        var result = new Post();
        return PostDto.builder()
                .title(result.getTitle())
                .content(result.getContent())
                .member(result.getMember())
                .createAt(result.getCreateAt())
                .updateAt(result.getUpdateAt())
                .build();
    }

    @Override
    public boolean delete(Long postSeq, Long memberSeq) {
        try {
            var post = postRepository.findById(postSeq).orElseThrow(()-> new RuntimeException("err"));
//            postRepository.delete(post);
            return true;
        }catch(Exception e) {
            return false;
        }


    }
}
