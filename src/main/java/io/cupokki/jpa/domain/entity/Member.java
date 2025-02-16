package io.cupokki.jpa.domain.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {

    public Member() {
        this.useYn = 1;
    }

    @Id
    private Long memberSeq;

    private String username;

    private String memberPw;

    private String regDate;

    private int useYn;

    private String email;
}
