package io.cupokki.jpa.service;

import io.cupokki.jpa.dto.PostCreateDto;
import io.cupokki.jpa.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public interface PostService {

    public Optional<PostCreateDto> save(PostCreateDto postCreateDto);

    //TODO : 추후에 Pageable
    public List<PostDto> findAll();

    public Optional<PostDto> findById(Long postSeq);

    public Optional<PostDto> findByKeyword(String keyword);

    //TODO : 더 좋은 구조를 생각해볼 것
    public boolean delete(Long postSeq, Long MemberSeq);

}
