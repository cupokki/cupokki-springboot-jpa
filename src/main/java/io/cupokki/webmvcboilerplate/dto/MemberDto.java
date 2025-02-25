package io.cupokki.webmvcboilerplate.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class MemberDto {
    private Long memberSeq;
    private String username;
    private LocalDateTime regDate;
    private int useYn;
    private String email;
}
