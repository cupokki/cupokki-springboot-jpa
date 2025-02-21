package io.cupokki.jpa.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

//TODO : 추후에 비즈니스로직에 따른 분리
@Getter
@Setter
public class PostDto {
    private Long postSeq;
    private String title;
    private String content;
    private Long memberSeq;
    private int publishYn;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

}
