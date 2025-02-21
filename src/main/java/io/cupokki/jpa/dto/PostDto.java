package io.cupokki.jpa.dto;

import io.cupokki.jpa.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    public PostDto () {};
    public PostDto(Post post) {
        this.postSeq = post.getPostSeq();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.memberSeq = post.getMemberSeq();
        this.publishYn = post.getPublishYn();
        this.createAt = post.getCreateAt();
        this.updateAt = post.getUpdateAt();
    }
}
