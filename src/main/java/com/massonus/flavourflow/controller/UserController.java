package com.massonus.flavourflow.controller;

import com.massonus.flavourflow.dto.UserDto;
import com.massonus.flavourflow.entity.User;
import com.massonus.flavourflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("bonuses", userService.getUserBonuses(user.getId()));
        return "user/profile";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/change-password")
    public String getChangePassword() {
        return "user/changePassword";
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/change-profile")
    @ResponseBody
    public UserDto updateUserProfile(@RequestBody UserDto userDto, @AuthenticationPrincipal User user) {

        return userService.editUser(userDto, user);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/add")
    public String getAddUserForm() {

        return "user/addUser";

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    @ResponseBody
    public UserDto addUser(@RequestBody UserDto userDto) {

        return userService.createUser(userDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit/{id}")
    public String getEditUserForm(@PathVariable Long id, Model model) {

        model.addAttribute("editUser", userService.getUserById(id));

        return "user/editUser";

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/edit")
    @ResponseBody
    public UserDto saveUpdatedUser(@RequestBody UserDto userDto) {

        return userService.editUser(userDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete")
    @ResponseBody
    public Long deleteUser(@RequestParam Long id) {

        return userService.deleteUser(id);
    }
}
