package io.cupokki.jpa.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberDto {
    private Long memberSeq;
    private String username;
    private String memberPw;
    private LocalDateTime regDate;
    private int useYn;
    private String email;
}
