package io.cupokki.jpa.service;

import io.cupokki.jpa.domain.dto.PostCreateDto;
import io.cupokki.jpa.domain.dto.PostDto;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public interface PostService {

    public Optional<PostCreateDto> save(PostCreateDto postCreateDto);

    //TODO : 추후에 Pageable
    public List<PostDto> findAll();

    public Optional<PostDto> findById();

    public Optional<PostDto> findByKeyword();

}
