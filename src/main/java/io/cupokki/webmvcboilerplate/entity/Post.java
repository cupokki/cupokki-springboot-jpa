package io.cupokki.webmvcboilerplate.entity;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import io.cupokki.webmvcboilerplate.dto.PostDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

//TODO : 추후에 빌더로 변경
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
public class Post {

    public Post() {
        publishYn = 1;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long postSeq;

    @NotNull
    private String title;

    @NotNull
    private String content;

    private String visibility;

    @ManyToOne
    private Member member;

    private int publishYn;

    @CreationTimestamp
    @NotNull
    private LocalDateTime createAt;

    @UpdateTimestamp
    @NotNull
    private LocalDateTime updateAt;
}
