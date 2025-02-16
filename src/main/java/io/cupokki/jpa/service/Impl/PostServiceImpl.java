package io.cupokki.jpa.service.Impl;

import io.cupokki.jpa.domain.dto.PostCreateDto;
import io.cupokki.jpa.domain.dto.PostDto;
import io.cupokki.jpa.service.PostService;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public class PostServiceImpl implements PostService {
    @Override
    public Optional<PostCreateDto> save(PostCreateDto postCreateDto) {
        return Optional.empty();
    }

    @Override
    public List<PostDto> findAll() {
        return List.of();
    }

    @Override
    public Optional<PostDto> findById() {
        return Optional.empty();
    }

    @Override
    public Optional<PostDto> findByKeyword() {
        return Optional.empty();
    }
}
