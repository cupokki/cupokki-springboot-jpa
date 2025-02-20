package io.cupokki.jpa.entity;

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
    private LocalDateTime create_at;

    @UpdateTimestamp
    private LocalDateTime update_at;

}
