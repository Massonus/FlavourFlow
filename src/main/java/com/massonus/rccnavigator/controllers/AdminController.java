package com.massonus.rccnavigator.controllers;

import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.service.CompanyService;
import com.massonus.rccnavigator.service.CompanyCountryService;
import com.massonus.rccnavigator.service.KitchenCategoryService;
import com.massonus.rccnavigator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final KitchenCategoryService kitchenCategoryService;
    private final CompanyService companyService;
    private final CompanyCountryService companyCountryService;

    @Autowired
    public AdminController(UserService userService, KitchenCategoryService kitchenCategoryService, CompanyService companyService, CompanyCountryService companyCountryService) {
        this.userService = userService;
        this.kitchenCategoryService = kitchenCategoryService;
        this.companyService = companyService;
        this.companyCountryService = companyCountryService;
    }

    @GetMapping("/panel")
    public String getUserList(@AuthenticationPrincipal User admin, Model model) {

        model.addAttribute("admin", admin);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("companyCountry", kitchenCategoryService.getAllCategories());
        model.addAttribute("types", companyCountryService.getAllTypes());
        model.addAttribute("companies", companyService.getAllCompanies());

        return "admin/adminPanel";
    }

}
