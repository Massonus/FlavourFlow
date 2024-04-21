package com.massonus.flavourflow.controller;

import com.massonus.flavourflow.entity.User;
import com.massonus.flavourflow.service.AnonymousUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;

@Controller
public class MainController {

    private final AnonymousUserService anonymousUserService;

    @Autowired
    public MainController(AnonymousUserService anonymousUserService) {
        this.anonymousUserService = anonymousUserService;
    }

    @GetMapping("/")
    public String getHome(HttpServletRequest request, @AuthenticationPrincipal User user) {

        if (Objects.isNull(user)) {
            anonymousUserService.saveAnonymous(request.getRemoteAddr());
        }

        return "index";
    }
}
