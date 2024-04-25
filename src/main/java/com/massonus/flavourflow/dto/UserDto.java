package com.massonus.flavourflow.dto;

import com.massonus.flavourflow.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long userId;

    private String username;

    private String email;

    private Role role;

    private String oldPassword;

    private String password;

    private String redactor;

    private Boolean isSuccess;

    private Boolean isIncorrectOldPassword;

    private Boolean isSameUsername;

    private Boolean isSameEmail;

    private String captchaResponse;

    private Boolean isSuccessCaptcha;

    private Double bonuses;
}
