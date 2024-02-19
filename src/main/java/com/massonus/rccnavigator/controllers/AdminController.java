package com.massonus.rccnavigator.controllers;

import com.massonus.rccnavigator.entity.KitchenCategory;
import com.massonus.rccnavigator.entity.Role;
import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.service.KitchenCategoryService;
import com.massonus.rccnavigator.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/add-new-user")
    public String registrationPost(@RequestParam String username,
                                   @RequestParam String password,
                                   @RequestParam String confirmPassword,
                                   @RequestParam String email,
                                   @RequestParam String role) {

        final User user = new User();

        user.setUsername(username);

        if (password.equals(confirmPassword)) {
            user.setPassword(password);
        } else {
            return "redirect:/admin/panel";
        }

        user.setEmail(email);

        if (role.equals("ADMIN")) {
            user.setRoles(Collections.singleton(Role.ADMIN));
            userService.addUser(user, true);
        } else {
            user.setRoles(Collections.singleton(Role.USER));
            userService.addUser(user, false);
        }

        return "redirect:/admin/panel";
    }

    @PostMapping("/add-new-category")
    public String addCategoryPost(@Valid KitchenCategory category) {

        kitchenCategoryService.saveCategory(category);

        return "redirect:/admin/panel";

    }


}
