package io.cupokki.jpa.service;

import io.cupokki.jpa.dto.PostCreateDto;
import io.cupokki.jpa.dto.PostDto;

import java.util.List;

//@Service

//컨트롤러에 옵셔널을 넘겨주면 안된다.
public interface PostService {

    public PostDto createPost(PostCreateDto postCreateDto);

    //TODO : 추후에 Pageable
    public List<PostDto> findAll();

    public PostDto getById(Long postSeq);

    public PostDto findByKeyword(String keyword);

    //TODO : 더 좋은 구조를 생각해볼 것, 이것도Dto?
    public boolean delete(Long postSeq, Long MemberSeq);

}
