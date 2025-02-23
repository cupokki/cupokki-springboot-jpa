package io.cupokki.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
public class Member {

    public Member() {
        this.useYn = 1;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long memberSeq;

    private String username;

    private String memberPw;

    @CreationTimestamp
    private LocalDateTime regDate;

    private int useYn;

    private String email;
}
