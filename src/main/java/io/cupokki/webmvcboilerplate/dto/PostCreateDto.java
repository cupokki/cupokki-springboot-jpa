package io.cupokki.webmvcboilerplate.dto;

import io.cupokki.webmvcboilerplate.entity.Member;
import io.cupokki.webmvcboilerplate.entity.Post;
import lombok.*;

import java.time.LocalDateTime;

//TODO : 추후에 비즈니스로직에 따른 분리
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PostCreateDto {
    private Long postSeq;
    private String title;
    private String content;
    private Member member;
    private int publishYn;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

}
