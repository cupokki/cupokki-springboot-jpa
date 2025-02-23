package io.cupokki.jpa.dto;

import lombok.Getter;

@Getter
public class MemberCreateDto {
    private String email;
    private String password;
    private String passwordCheck;
    private boolean isDuplicate;
}
