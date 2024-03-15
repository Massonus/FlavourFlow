package com.massonus.rccnavigator.controller;


import com.massonus.rccnavigator.dto.CaptchaResponseDto;
import com.massonus.rccnavigator.dto.UserDto;
import com.massonus.rccnavigator.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Objects;

@Controller
public class RegistrationController {

    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    @Value("${recaptcha.secret}")
    private String secret;

    private final UserService userService;

    private final RestTemplate restTemplate;

    @Autowired
    public RegistrationController(UserService userService, RestTemplate restTemplate) {
        this.userService = userService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/reg")
    public String registration() {
        return "user/registration";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @PostMapping("/registration")
    @ResponseBody
    public UserDto registrationPost(@RequestBody UserDto userDto) {

        String url = String.format(CAPTCHA_URL, secret, userDto.getCaptchaResponse());
        CaptchaResponseDto response = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);
        if (!Objects.requireNonNull(response).isSuccess()) {
            userDto.setIsSuccessCaptcha(false);
            return userDto;
        }

        return userService.registrationUser(userDto);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            request.getSession().invalidate();
        }
        return "redirect:/";
    }

}
