package com.massonus.rccnavigator.controllers;

import com.massonus.rccnavigator.entity.Role;
import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        return "user/profile";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/change-profile")
    public String updateUserProfile(@AuthenticationPrincipal User user,
                                    @RequestParam String username,
                                    @RequestParam String email,
                                    @RequestParam String password) {


        userService.updateUser(user.getId(), password, username, email);


        return "redirect:/user/profile";

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);

        return "redirect:/admin/panel";

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/add-new-user")
    public String getAddUserForm() {

        return "user/addUser";

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add-new-user")
    public String registrationPost(@AuthenticationPrincipal User redactor,
                                   @RequestParam String username,
                                   @RequestParam String password,
                                   @RequestParam String confirmPassword,
                                   @RequestParam String email,
                                   @RequestParam String role) {

        final User user = new User();
        user.setRedactor(redactor.getId());
        user.setUsername(username);

        if (password.equals(confirmPassword)) {
            user.setPassword(password);
        } else {
            return "redirect:/admin/panel";
        }

        user.setEmail(email);

        if (role.equals("ADMIN")) {
            user.setRoles(Collections.singleton(Role.ADMIN));
            userService.saveUser(user, true);
        } else {
            user.setRoles(Collections.singleton(Role.USER));
            userService.saveUser(user, false);
        }

        return "redirect:/admin/panel";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit-user/{id}")
    public String getEditUserForm(@PathVariable Long id, Model model) {

        User userById = userService.getUserById(id);

        model.addAttribute("user", userById);

        return "user/editUser";

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/edit-user/{id}")
    public String saveUpdatedUser(@AuthenticationPrincipal User redactor,
                                  @PathVariable Long id,
                                  @RequestParam String username,
                                  @RequestParam String email,
                                  @RequestParam String password,
                                  @RequestParam String role) {

        userService.editUser(redactor, id, username, email, password, Role.valueOf(role));

        return "redirect:/admin/panel";
    }

}
