package com.massonus.rccnavigator.controller;

import com.massonus.rccnavigator.dto.ItemType;
import com.massonus.rccnavigator.entity.Company;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.Objects;

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
    public String getAdminPanel(Model model, @AuthenticationPrincipal User admin,
                                @RequestParam(required = false) Long checkId,
                                @RequestParam(required = false) ItemType itemType,
                                @RequestParam(required = false) Boolean isAfterAlert) {

        model.addAttribute("admin", admin);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("countries", countryService.getAllCountries());
        model.addAttribute("companies", companyService.getAllCompanies().stream().sorted(Comparator.comparing(Company::getId)).toList());

        if (Objects.nonNull(checkId)) {
            model.addAttribute("alertModal", "modal open");
            model.addAttribute("checkId", checkId);
            model.addAttribute("itemType", itemType);
        }

        if (Objects.nonNull(itemType) && itemType.equals(ItemType.KITHCENCATEGORY)) {
            model.addAttribute("alertCompanies", companyService.getCompaniesByCategoryId(checkId));
            model.addAttribute("size", companyService.getCompaniesByCategoryId(checkId).size());
            model.addAttribute("existCountries", categoryService.getAllCategoriesExceptOne(checkId));
        }

        if (Objects.nonNull(itemType) && itemType.equals(ItemType.COMPANYCOUNTRY)) {
            model.addAttribute("alertCompanies", companyService.getCompaniesByCountryId(checkId));
            model.addAttribute("size", companyService.getCompaniesByCountryId(checkId));
            model.addAttribute("existCountries", countryService.getAllCountriesExceptOne(checkId));
        }

        if (Objects.nonNull(isAfterAlert)) {
            model.addAttribute("alertModal", "modal");
            model.addAttribute("chooseModal", "modal open");
        }

        return "admin/adminPanel";
    }

}
