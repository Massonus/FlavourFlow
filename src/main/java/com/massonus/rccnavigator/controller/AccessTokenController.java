package com.massonus.rccnavigator.controller;

import com.massonus.rccnavigator.entity.AccessToken;
import com.massonus.rccnavigator.service.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/token")
public class AccessTokenController {

    private final AccessTokenService tokenService;

    @Autowired
    public AccessTokenController(AccessTokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/add")
    @ResponseBody
    public AccessToken addCategoryPost(@RequestParam String token) {

        return tokenService.saveToken(token);

    }
}
