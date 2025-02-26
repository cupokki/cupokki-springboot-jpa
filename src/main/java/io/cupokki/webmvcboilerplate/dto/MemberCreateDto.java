package io.cupokki.webmvcboilerplate.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCreateDto {
    private String email;
    private String password;
    private String confirmPassword;
    private boolean isDuplicate;
}
