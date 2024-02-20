package com.massonus.rccnavigator.controllers;

import com.massonus.rccnavigator.entity.CompanyType;
import com.massonus.rccnavigator.entity.KitchenCategory;
import com.massonus.rccnavigator.entity.Role;
import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.service.CompanyCategoryService;
import com.massonus.rccnavigator.service.CompanyService;
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
    private final CompanyService companyService;
    private final CompanyCategoryService companyCategoryService;

    @Autowired
    public AdminController(UserService userService, KitchenCategoryService kitchenCategoryService, CompanyService companyService, CompanyCategoryService companyCategoryService) {
        this.userService = userService;
        this.kitchenCategoryService = kitchenCategoryService;
        this.companyService = companyService;
        this.companyCategoryService = companyCategoryService;
    }

    @GetMapping("/panel")
    public String getUserList(@AuthenticationPrincipal User admin, Model model) {

        model.addAttribute("admin", admin);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("categories", kitchenCategoryService.getAllCategories());
        model.addAttribute("types", companyCategoryService.getAllTypes());
        model.addAttribute("companies", companyService.getAllCompanies());

        return "admin/adminPanel";
    }

    @GetMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);

        return "redirect:/admin/panel";

    }

    @GetMapping("/add-new-user")
    public String getAddUserForm() {

        return "admin/addUser";

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

    @GetMapping("/add-new-category")
    public String getAddCategoryForm() {

        return "admin/addCategory";

    }

    @PostMapping("/add-new-category")
    public String addCategoryPost(@Valid KitchenCategory category) {

        kitchenCategoryService.saveKitchenCategory(category);

        return "redirect:/admin/panel";

    }

    @GetMapping("/edit-user/{id}")
    public String getEditUserForm(@PathVariable Long id, Model model) {

        User userById = userService.getUserById(id);

        model.addAttribute("user", userById);

        return "admin/editUser";

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

    @GetMapping("/edit-category/{id}")
    public String getCategoryEditForm(@PathVariable Long id, Model model) {

        model.addAttribute("category", kitchenCategoryService.getCategoryById(id));

        return "admin/editCategory";

    }

    @PostMapping("/edit-category/{id}")
    public String categoryPostEdit(@PathVariable Long id,
                                   @RequestParam String title) {

        kitchenCategoryService.editCategory(id, title);

        return "redirect:/admin/panel";
    }

    @GetMapping("/delete-category/{id}")
    public String deleteCategory(@PathVariable Long id) {

        kitchenCategoryService.deleteCategory(id);

        return "redirect:/admin/panel";

    }

    @GetMapping("/add-company")
    public String getAddCompanyForm(Model model) {

        model.addAttribute("categories", kitchenCategoryService.getAllCategories());
        model.addAttribute("types", companyCategoryService.getAllTypes());

        return "company/addCompany";

    }


    @GetMapping("/add-new-type")
    public String getAddTypeForm() {

        return "admin/addType";

    }

    @PostMapping("/add-new-type")
    public String addTypePost(@Valid CompanyType type) {

        companyCategoryService.saveCompanyCategory(type);

        return "redirect:/admin/panel";

    }


    @GetMapping("/edit-type/{id}")
    public String getTypeEditForm(@PathVariable Long id, Model model) {

        model.addAttribute("type", companyCategoryService.getTypeById(id));

        return "admin/editType";

    }

    @PostMapping("/edit-type/{id}")
    public String typePostEdit(@PathVariable Long id,
                                   @RequestParam String title) {

        companyCategoryService.editType(id, title);

        return "redirect:/admin/panel";
    }

    @GetMapping("/delete-type/{id}")
    public String deleteType(@PathVariable Long id) {

        companyCategoryService.deleteType(id);

        return "redirect:/admin/panel";

    }

}
