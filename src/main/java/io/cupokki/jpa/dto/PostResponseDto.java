package io.cupokki.jpa.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

//TODO : 추후에 비즈니스로직에 따른 분리
@Getter
@Setter
public class PostResponseDto {
    private Long postSeq;
    private String title;
    private String content;
    private Long memberSeq;
    private int publishYn;
    private LocalDateTime create_at;
    private LocalDateTime update_at;

}
