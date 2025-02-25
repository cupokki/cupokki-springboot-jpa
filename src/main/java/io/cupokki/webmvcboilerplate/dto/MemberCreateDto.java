package io.cupokki.webmvcboilerplate.dto;

import lombok.Getter;

@Getter
public class MemberCreateDto {
    private String email;
    private String password;
    private String passwordCheck;
    private boolean isDuplicate;
}
