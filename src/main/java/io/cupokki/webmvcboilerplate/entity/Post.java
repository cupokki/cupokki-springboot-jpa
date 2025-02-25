package io.cupokki.webmvcboilerplate.entity;

import io.cupokki.webmvcboilerplate.dto.PostDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

//TODO : 추후에 빌더로 변경
@Getter
@Setter
@Entity
public class Post {

    public Post() {
        publishYn = 1;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long postSeq;

    private String title;

    private String content;

    @Column
    private Long memberSeq;

    private int publishYn;

    @CreationTimestamp
    private LocalDateTime createAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;

    public PostDto fromPostDto(Long postSeq, String title, String content, Long memberSeq, LocalDateTime createAt, LocalDateTime updateAt) {
        PostDto dto = new PostDto();
        dto.setPostSeq(postSeq);
        dto.setTitle(title);
        dto.setContent(content);
        dto.setMemberSeq(memberSeq);
        dto.setCreateAt(createAt);
        dto.setUpdateAt(updateAt);
        return dto;
    }
}
