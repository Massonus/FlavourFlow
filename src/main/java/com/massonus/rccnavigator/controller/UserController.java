package com.massonus.rccnavigator.controller;

import com.massonus.rccnavigator.dto.UserDto;
import com.massonus.rccnavigator.entity.Role;
import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.service.UserService;
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
    @GetMapping("/add")
    public String getAddUserForm() {

        return "user/addUser";

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    @ResponseBody
    public User addUser(@RequestBody UserDto userDto) {

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
    public User saveUpdatedUser(@RequestBody UserDto userDto) {

        return userService.editUser(userDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete")
    @ResponseBody
    public Long deleteUser(@RequestParam Long id) {

        return userService.deleteUser(id);
    }

}
