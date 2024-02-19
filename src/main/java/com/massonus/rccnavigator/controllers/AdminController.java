package com.massonus.rccnavigator.controllers;

import com.massonus.rccnavigator.entity.*;
import com.massonus.rccnavigator.service.KitchenCategoryService;
import com.massonus.rccnavigator.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final KitchenCategoryService kitchenCategoryService;

    @Autowired
    public AdminController(UserService userService, KitchenCategoryService kitchenCategoryService) {
        this.userService = userService;
        this.kitchenCategoryService = kitchenCategoryService;
    }

    @GetMapping("/panel")
    public String getUserList(@AuthenticationPrincipal User admin, Model model) {

        model.addAttribute("admin", admin);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("categories", kitchenCategoryService.getAllCategories());

        return "admin/adminPanel";
    }

    @GetMapping("/add-new-user")
    public String getAddUserForm() {

        return "admin/addUser";

    }

    @GetMapping("/add-new-category")
    public String getAddCategoryForm() {

        return "admin/addCategory";

    }

    @GetMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);

        return "redirect:/admin/panel";

    }

    @GetMapping("/edit-user/{id}")
    public String getEditUserForm(@PathVariable Long id, Model model) {

        User userById = userService.getUserById(id);

        model.addAttribute("user", userById);

        return "admin/editUser";

    }

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

    @PostMapping("/add-new-category")
    public String addCategoryPost(@Valid KitchenCategory category) {

        kitchenCategoryService.saveCategory(category);

        return "redirect:/admin/panel";

    }

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
