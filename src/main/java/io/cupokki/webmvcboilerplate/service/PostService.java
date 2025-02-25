package io.cupokki.webmvcboilerplate.service;

import io.cupokki.webmvcboilerplate.dto.PostCreateDto;
import io.cupokki.webmvcboilerplate.dto.PostDto;

import java.util.List;

//@Service

//컨트롤러에 옵셔널을 넘겨주면 안된다.
public interface PostService {

    public PostDto createPost(PostCreateDto postCreateDto);

    //TODO : 추후에 Pageable
    public List<PostDto> getAll();

    public PostDto getById(Long postSeq);

    public PostDto searchByKeyword(String keyword);

    public boolean delete(Long postSeq, Long memberSeq);

}
