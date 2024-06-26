package com.massonus.flavourflow.controller;


import com.massonus.flavourflow.dto.UserDto;
import com.massonus.flavourflow.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/reg")
    public String registration() {
        return "user/registration";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) boolean error, Model model) {
        model.addAttribute("error", error);

        return "user/login";
    }

    @PostMapping("/registration")
    @ResponseBody
    public UserDto registrationPost(@RequestBody UserDto userDto) {

        if (!userService.checkCaptcha(userDto)) {
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
