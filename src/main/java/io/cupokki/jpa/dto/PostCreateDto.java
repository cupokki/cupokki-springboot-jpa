package io.cupokki.jpa.dto;

import io.cupokki.jpa.entity.Post;
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
    private Long memberSeq;
    private int publishYn;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public PostCreateDto(Post post) {
        this.postSeq = post.getPostSeq();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.memberSeq = post.getMemberSeq();
        this.publishYn = post.getPublishYn();
        this.createAt = post.getCreateAt();
        this.updateAt = post.getUpdateAt();
    }
}
