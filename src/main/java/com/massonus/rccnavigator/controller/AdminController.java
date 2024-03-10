package com.massonus.rccnavigator.controller;

import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.service.CompanyCountryService;
import com.massonus.rccnavigator.service.CompanyService;
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
    private final KitchenCategoryService categoryService;
    private final CompanyService companyService;
    private final CompanyCountryService countryService;

    @Autowired
    public AdminController(UserService userService, KitchenCategoryService categoryService, CompanyService companyService, CompanyCountryService countryService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.companyService = companyService;
        this.countryService = countryService;
    }

    @GetMapping("/panel")
    public String getUserList(@AuthenticationPrincipal User admin, Model model) {

        model.addAttribute("admin", admin);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("companyCountry", categoryService.getAllCategories());
        model.addAttribute("countries", countryService.getAllCountries());
        model.addAttribute("companies", companyService.getAllCompanies());

        return "admin/adminPanel";
    }

}
