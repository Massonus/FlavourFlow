package com.massonus.rccnavigator.dto;

import com.massonus.rccnavigator.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long userId;

    private String username;

    private String email;

    private Role role;

    private String password;

    private Long redactor;

    private Boolean isSameUsername;

    private Boolean isSameEmail;
}
