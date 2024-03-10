package com.massonus.rccnavigator.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long userId;

    private String username;

    private String email;

    private String role;

    private String password;

    private Long redactor;
}
