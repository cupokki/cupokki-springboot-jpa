package io.cupokki.webmvcboilerplate.dto;

import io.cupokki.webmvcboilerplate.entity.Member;
import io.cupokki.webmvcboilerplate.entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

//TODO : 추후에 비즈니스로직에 따른 분리
@Getter
@Setter
@Builder
public class PostDto {
    private Long postSeq;
    private String title;
    private String content;
    private Member member;
    private int publishYn;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
