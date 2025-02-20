package io.cupokki.jpa.service.Impl;

import io.cupokki.jpa.dto.PostCreateDto;
import io.cupokki.jpa.dto.PostDto;
import io.cupokki.jpa.dto.PostResponseDto;
import io.cupokki.jpa.service.PostService;

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
    public Optional<PostDto> findById(Long postSeq) {
        return Optional.empty();
    }

    @Override
    public Optional<PostDto> findByKeyword(String keyword) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long postSeq, Long MemberSeq) {
        return false;
    }
}
