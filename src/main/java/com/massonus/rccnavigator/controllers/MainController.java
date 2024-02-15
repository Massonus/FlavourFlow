package com.massonus.rccnavigator.controllers;

import com.massonus.rccnavigator.entity.Tree;
import com.massonus.rccnavigator.entity.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;

@Controller
public class MainController {

    @GetMapping(value = "/static/css/{cssFile}")
    public @ResponseBody byte[] getFile(@PathVariable("cssFile") String cssFile) {


        try (InputStream in = getClass()
                .getResourceAsStream("/templates/" + cssFile)) {
            return Objects.requireNonNull(in).readAllBytes();

        } catch (Exception e) {
            String error = "ERROR: css file (/templates/" + cssFile + ") not found";
            return error.getBytes();
        }
    }

    @GetMapping("/")
    public String getHome() {

        return "index";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
